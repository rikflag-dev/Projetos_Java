package simples.JogoMemoria.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import simples.JogoMemoria.util.Ranking;

public class Tabuleiro extends JFrame {

    private CartaBotao primeiraCarta, segundaCarta;
    private boolean podeClicar = true;
    private JLabel labelTempo;
    private int tempo = 0;
    private Timer cronometro;
    private int paresEncontrados = 0;
    private final int totalDePares = 8;

    public Tabuleiro() {
        setTitle("Jogo da Memória");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelSuperior = new JPanel();
        labelTempo = new JLabel("Tempo: 0s");
        painelSuperior.add(labelTempo);
        add(painelSuperior, BorderLayout.NORTH);

        JPanel painelCartas = new JPanel(new GridLayout(4, 4));
        List<Integer> valores = new ArrayList<>();
        for (int i = 1; i <= totalDePares; i++) {
            valores.add(i);
            valores.add(i);
        }
        Collections.shuffle(valores);

        for (int valor : valores) {
            CartaBotao carta = new CartaBotao(valor);
            carta.addActionListener(e -> clicarCarta(carta));
            painelCartas.add(carta);
        }

        add(painelCartas, BorderLayout.CENTER);

        iniciarCronometro();
        setVisible(true);
    }

    private void clicarCarta(CartaBotao carta) {
        if (!podeClicar || carta.foiEncontrada() || carta == primeiraCarta) {
            return;
        }

        carta.mostrarValor();

        if (primeiraCarta == null) {
            primeiraCarta = carta;
        } else {
            segundaCarta = carta;
            podeClicar = false;

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    verificarPar();
                    podeClicar = true;
                }
            }, 800);
        }
    }

    private void verificarPar() {
        if (primeiraCarta.getValor() == segundaCarta.getValor()) {
            primeiraCarta.setEncontrada(true);
            segundaCarta.setEncontrada(true);
            paresEncontrados++;

            if (paresEncontrados == totalDePares) {
                pararCronometro();
                int tempoFinal = tempo;
                SwingUtilities.invokeLater(() -> {
                    String nome = JOptionPane.showInputDialog("Parabéns! Você finalizou em " + tempoFinal + "s\nDigite seu nome para o ranking:");
                    if (nome != null && !nome.trim().isEmpty()) {
                        Ranking.salvar(nome, tempoFinal);
                    }
                    Ranking.exibir(this);
                });
            }
        } else {
            primeiraCarta.esconderValor();
            segundaCarta.esconderValor();
        }

        primeiraCarta = null;
        segundaCarta = null;
    }

    private void iniciarCronometro() {
        cronometro = new Timer();
        cronometro.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                tempo++;
                SwingUtilities.invokeLater(() -> labelTempo.setText("Tempo: " + tempo + "s"));
            }
        }, 1000, 1000);
    }

    private void pararCronometro() {
        cronometro.cancel();
    }
}
