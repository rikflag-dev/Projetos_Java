package Intermediarios.Financas.ui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Gerenciador de Finanças Pessoais");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        aplicarTema();

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("💰 Transações", new PainelCadastro());
        abas.addTab("📊 Relatórios", new PainelRelatorios());

        add(abas);

        setVisible(true);
    }

    private void aplicarTema() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Tema escuro não aplicado, usando padrão.");
        }
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
