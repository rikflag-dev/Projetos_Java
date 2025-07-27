package simples.CofreVirtual.view;

import simples.CofreVirtual.controller.CofreController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// AWT (layout e containers)
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CofreGUI extends JFrame {
    private JTextArea areaTexto;
    private JPasswordField campoSenha;
    private JPanel painelLogin, painelCofre;
    private final CofreController controller = new CofreController();

    public CofreGUI() {
        setTitle("🔐 Cofre Virtual");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        criarPainelLogin();
        criarPainelCofre();

        add(painelLogin, "login");
        add(painelCofre, "cofre");

        mostrarPainel("login");
    }

    private void criarPainelLogin() {
        painelLogin = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Digite a senha mestra:");
        campoSenha = new JPasswordField();
        JButton btnEntrar = new JButton("Entrar");

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(campoSenha.getPassword());
                if (controller.autenticar(senha)) {
                    areaTexto.setText(controller.carregarTexto());
                    mostrarPainel("cofre");
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel p = new JPanel(new GridLayout(3, 1));
        p.add(lbl);
        p.add(campoSenha);
        p.add(btnEntrar);

        painelLogin.add(p, BorderLayout.CENTER);
    }

    private void criarPainelCofre() {
        painelCofre = new JPanel(new BorderLayout());
        areaTexto = new JTextArea();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnSair = new JButton("Sair");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.salvarTexto(areaTexto.getText());
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso.");
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoSenha.setText("");
                mostrarPainel("login");
            }
        });

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnSair);

        painelCofre.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        painelCofre.add(botoes, BorderLayout.SOUTH);
    }

    private void mostrarPainel(String nome) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), nome);
    }
}

