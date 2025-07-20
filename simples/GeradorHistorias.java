package simples;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.Random;

public class GeradorHistorias extends JFrame {

    JComboBox<String> generoBox;
    JTextArea resultadoArea;
    JButton botaoGerar;

    public GeradorHistorias() {
        setTitle("Gerador de Histórias");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar componentes
        generoBox = new JComboBox<>(new String[]{"Comédia", "Terror", "Fantasia"});
        botaoGerar = new JButton("Gerar História");
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);

        // Painel de cima
        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Gênero:"));
        painelTopo.add(generoBox);
        painelTopo.add(botaoGerar);

        // Adicionar à janela
        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        // Ação do botão
        botaoGerar.addActionListener(e -> {
            String genero = generoBox.getSelectedItem().toString();
            resultadoArea.setText(gerarHistoria(genero));
        });
    }

    // Método para gerar a história com base no gênero
    String gerarHistoria(String genero) {
        Random rand = new Random();

        String[] inicio, meio, fim;

        if (genero.equals("Comédia")) {
            inicio = new String[]{"João tropeçou", "Maria cantou alto", "Um pato correu"};
            meio   = new String[]{"no shopping", "na escola", "durante um casamento"};
            fim    = new String[]{"e virou meme!", "e todos aplaudiram!", "e caiu no TikTok!"};
        } else if (genero.equals("Terror")) {
            inicio = new String[]{"As luzes se apagaram", "Alguém bateu na porta", "O boneco sorriu"};
            meio   = new String[]{"à meia-noite", "em um hospital abandonado", "durante uma tempestade"};
            fim    = new String[]{"e ninguém sobreviveu.", "e o medo começou.", "e o espírito apareceu."};
        } else {
            inicio = new String[]{"Um dragão apareceu", "A princesa fugiu", "Um mago lançou magia"};
            meio   = new String[]{"numa floresta mágica", "num castelo flutuante", "na ponte dos elfos"};
            fim    = new String[]{"e salvou o reino!", "e começou uma nova era!", "e tudo virou lenda."};
        }

        return inicio[rand.nextInt(inicio.length)] + " " +
               meio[rand.nextInt(meio.length)] + " " +
               fim[rand.nextInt(fim.length)];
    }

    public static void main(String[] args) {
        new GeradorHistorias().setVisible(true);
    }
}
