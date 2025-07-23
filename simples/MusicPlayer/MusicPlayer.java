import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.FloatControl;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

public class MusicPlayer extends JFrame {
    private JButton btnTocar, btnParar, btnAdicionar, btnSalvarPlaylist, btnCarregarPlaylist;
    private JList<String> listaMusicas;
    private DefaultListModel<String> modeloLista;
    private JSlider barraVolume;
    private ArrayList<File> filaMusicas;
    private int indiceAtual = 0;
    private AdvancedPlayer player;
    private Thread threadMusica;
    private File playlistAtual;

    public MusicPlayer() {
        setTitle("Tocador de Música com Playlist e Volume");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        listaMusicas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaMusicas);

        btnTocar = new JButton("Tocar");
        btnParar = new JButton("Parar");
        btnAdicionar = new JButton("Adicionar Música");
        btnSalvarPlaylist = new JButton("Salvar Playlist");
        btnCarregarPlaylist = new JButton("Carregar Playlist");

        barraVolume = new JSlider(0, 100, 80);
        barraVolume.setPreferredSize(new Dimension(150, 20));

        JPanel painelTopo = new JPanel();
        painelTopo.add(btnAdicionar);
        painelTopo.add(btnSalvarPlaylist);
        painelTopo.add(btnCarregarPlaylist);

        JPanel painelControles = new JPanel();
        painelControles.add(btnTocar);
        painelControles.add(btnParar);
        painelControles.add(new JLabel("Volume:"));
        painelControles.add(barraVolume);

        add(scrollPane, BorderLayout.CENTER);
        add(painelTopo, BorderLayout.NORTH);
        add(painelControles, BorderLayout.SOUTH);

        filaMusicas = new ArrayList<>();
        criarPastaPlaylists();

        btnAdicionar.addActionListener(e -> adicionarMusica());
        btnSalvarPlaylist.addActionListener(e -> salvarPlaylist());
        btnCarregarPlaylist.addActionListener(e -> carregarPlaylist());
        btnTocar.addActionListener(e -> tocarMusicaSelecionada());
        btnParar.addActionListener(e -> pararMusica());

        // AVISO: controle de volume não funciona com AdvancedPlayer direto.
        barraVolume.addChangeListener(e -> {
            int volume = barraVolume.getValue();
            // Aqui você poderia tentar implementar volume se usasse outra biblioteca
            // Ou tentar "mutar" (stop/play) mas não há suporte real aqui.
            // Deixo o slider por enquanto só visual.
        });

        setVisible(true);
    }

    private void criarPastaPlaylists() {
        File pasta = new File("playlists");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    private void adicionarMusica() {
        JFileChooser seletor = new JFileChooser();
        seletor.setMultiSelectionEnabled(true);
        seletor.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = seletor.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File[] selecionados = seletor.getSelectedFiles();
            for (File f : selecionados) {
                if (f.getName().toLowerCase().endsWith(".mp3")) {
                    filaMusicas.add(f);
                    modeloLista.addElement(f.getName());
                }
            }
        }
    }

    private void salvarPlaylist() {
        String nome = JOptionPane.showInputDialog(this, "Nome da playlist:");
        if (nome == null || nome.isBlank()) return;
        try {
            File arquivo = new File("playlists/" + nome + ".txt");
            PrintWriter pw = new PrintWriter(arquivo);
            for (File f : filaMusicas) {
                pw.println(f.getAbsolutePath());
            }
            pw.close();
            JOptionPane.showMessageDialog(this, "Playlist salva com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar playlist.");
        }
    }

    private void carregarPlaylist() {
        JFileChooser seletor = new JFileChooser(new File("playlists"));
        int resultado = seletor.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                File arq = seletor.getSelectedFile();
                playlistAtual = arq;
                modeloLista.clear();
                filaMusicas.clear();
                BufferedReader br = new BufferedReader(new FileReader(arq));
                String linha;
                while ((linha = br.readLine()) != null) {
                    File f = new File(linha);
                    if (f.exists()) {
                        filaMusicas.add(f);
                        modeloLista.addElement(f.getName());
                    }
                }
                br.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar playlist.");
            }
        }
    }

    private void tocarMusicaSelecionada() {
        indiceAtual = listaMusicas.getSelectedIndex();
        if (indiceAtual == -1 && !filaMusicas.isEmpty()) {
            indiceAtual = 0;
        }
        tocarIndice(indiceAtual);
    }

    private void tocarIndice(int i) {
        if (i < 0 || i >= filaMusicas.size()) return;

        pararMusica();
        File musica = filaMusicas.get(i);

        threadMusica = new Thread(() -> {
            try {
                FileInputStream fis = new FileInputStream(musica);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new AdvancedPlayer(bis);
                player.setPlayBackListener(new PlaybackListener() {
                    public void playbackFinished(PlaybackEvent evt) {
                        if (indiceAtual + 1 < filaMusicas.size()) {
                            SwingUtilities.invokeLater(() -> {
                                indiceAtual++;
                                listaMusicas.setSelectedIndex(indiceAtual);
                                tocarIndice(indiceAtual);
                            });
                        }
                    }
                });
                player.play();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao tocar: " + e.getMessage());
            }
        });

        threadMusica.start();
    }

    private void pararMusica() {
        if (player != null) {
            player.close();
        }
        if (threadMusica != null && threadMusica.isAlive()) {
            threadMusica.interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicPlayer::new);
    }
}
