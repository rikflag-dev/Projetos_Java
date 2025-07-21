package simples.RPGTexto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class InterfaceJogo {
    private JFrame janela;
    private JTextArea areaHistoria;
    private JButton botao1, botao2;
    private JLabel estatisticas;
    private Jogador jogador;
    private Inimigo inimigo;

    public InterfaceJogo() {
        jogador = new Jogador("Mago", 100, 50);

        janela = new JFrame("RPG de Texto - Swing");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 400);
        janela.setLayout(new BorderLayout());

        estatisticas = new JLabel(obterEstatisticasJogador());
        areaHistoria = new JTextArea("Você acorda em uma floresta misteriosa...");
        areaHistoria.setEditable(false);
        areaHistoria.setLineWrap(true);
        areaHistoria.setWrapStyleWord(true);

        botao1 = new JButton("Explorar a floresta");
        botao2 = new JButton("Descansar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botao1);
        painelBotoes.add(botao2);

        janela.add(estatisticas, BorderLayout.NORTH);
        janela.add(new JScrollPane(areaHistoria), BorderLayout.CENTER);
        janela.add(painelBotoes, BorderLayout.SOUTH);

        botao1.addActionListener(e -> explorar());
        botao2.addActionListener(e -> descansar());

        janela.setVisible(true);
    }

    private void explorar() {
        inimigo = new Inimigo("Goblin", 40, 0);
        areaHistoria.append("\n\nVocê encontrou um " + inimigo.getNome() + "!");
        botao1.setText("Atacar com magia");
        botao2.setText("Fugir");

        for (ActionListener al : botao1.getActionListeners()) botao1.removeActionListener(al);
        for (ActionListener al : botao2.getActionListeners()) botao2.removeActionListener(al);

        botao1.addActionListener(e -> atacar());
        botao2.addActionListener(e -> fugir());
    }

    private void atacar() {
        if (jogador.getMana() < 10) {
            areaHistoria.append("\nVocê não tem mana suficiente!");
            return;
        }

        jogador.usarMana(10);
        int dano = (int)(Math.random() * 20 + 10);
        inimigo.receberDano(dano);
        areaHistoria.append("\nVocê lançou uma magia e causou " + dano + " de dano!");

        if (inimigo.estaMorto()) {
            areaHistoria.append("\nVocê derrotou o inimigo!");
            verificarFim(true);
        } else {
            ataqueInimigo();
        }

        estatisticas.setText(obterEstatisticasJogador());
    }

    private void ataqueInimigo() {
        int dano = (int)(Math.random() * 15 + 5);
        jogador.receberDano(dano);
        areaHistoria.append("\n" + inimigo.getNome() + " atacou e causou " + dano + " de dano!");

        if (jogador.estaMorto()) {
            areaHistoria.append("\nVocê foi derrotado...");
            verificarFim(false);
        }

        estatisticas.setText(obterEstatisticasJogador());
    }

    private void fugir() {
        areaHistoria.append("\nVocê conseguiu fugir com sucesso.");
        resetarBotoes();
    }

    private void descansar() {
        jogador.curar(10);
        jogador.recuperarMana(10);
        areaHistoria.append("\nVocê descansou e recuperou vida e mana.");
        estatisticas.setText(obterEstatisticasJogador());
    }

    private void verificarFim(boolean vitoria) {
        botao1.setEnabled(false);
        botao2.setEnabled(false);
        if (vitoria) {
            areaHistoria.append("\nFinal: Você sobreviveu ao desafio!");
        } else {
            areaHistoria.append("\nFinal: Sua jornada terminou aqui.");
        }
    }

    private void resetarBotoes() {
        botao1.setText("Explorar a floresta");
        botao2.setText("Descansar");

        for (ActionListener al : botao1.getActionListeners()) botao1.removeActionListener(al);
        for (ActionListener al : botao2.getActionListeners()) botao2.removeActionListener(al);

        botao1.addActionListener(e -> explorar());
        botao2.addActionListener(e -> descansar());
    }

    private String obterEstatisticasJogador() {
        return "Vida: " + jogador.getVida() + " | Mana: " + jogador.getMana();
    }
}

