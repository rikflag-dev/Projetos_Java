package Intermediarios.Financas.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import Intermediarios.Financas.Dados.DadosFinanceiros;
import Intermediarios.Financas.Modelo.Transacao;

public class PainelCadastro extends JPanel {

    private JTextField campoDescricao;
    private JTextField campoValor;
    private JComboBox<String> comboCategoria;
    private JCheckBox checkEntrada;
    private JButton botaoSalvar;

    public PainelCadastro() {
        configurarPainel();
        criarComponentes();
        configurarEventos();
    }

    private void configurarPainel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBackground(new Color(45, 45, 45));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    private void criarComponentes() {
        JLabel rotuloDescricao = new JLabel("Descrição:");
        rotuloDescricao.setForeground(Color.WHITE);
        rotuloDescricao.setFont(new Font("Arial", Font.BOLD, 14));
        campoDescricao = new JTextField();

        JLabel rotuloValor = new JLabel("Valor:");
        rotuloValor.setForeground(Color.WHITE);
        rotuloValor.setFont(new Font("Arial", Font.BOLD, 14));
        campoValor = new JTextField();

        JLabel rotuloCategoria = new JLabel("Categoria:");
        rotuloCategoria.setForeground(Color.WHITE);
        rotuloCategoria.setFont(new Font("Arial", Font.BOLD, 14));
        comboCategoria = new JComboBox<>(new String[] {
            "Alimentação", "Transporte", "Educação", "Lazer", "Saúde", "Outros"
        });

        JLabel rotuloTipo = new JLabel("É entrada?");
        rotuloTipo.setForeground(Color.WHITE);
        rotuloTipo.setFont(new Font("Arial", Font.BOLD, 14));
        checkEntrada = new JCheckBox();
        checkEntrada.setBackground(new Color(45, 45, 45));
        checkEntrada.setForeground(Color.WHITE);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBackground(new Color(70, 130, 180));
        botaoSalvar.setForeground(Color.WHITE);
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 16));

        add(rotuloDescricao);
        add(campoDescricao);
        add(rotuloValor);
        add(campoValor);
        add(rotuloCategoria);
        add(comboCategoria);
        add(rotuloTipo);
        add(checkEntrada);
        add(new JLabel(""));
        add(botaoSalvar);
    }

    private void configurarEventos() {
        botaoSalvar.addActionListener(e -> {
            String descricao = campoDescricao.getText().trim();
            String valorTexto = campoValor.getText().trim();
            String categoria = (String) comboCategoria.getSelectedItem();
            boolean entrada = checkEntrada.isSelected();

            if (descricao.isEmpty() || valorTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valor;
            try {
                valor = Double.parseDouble(valorTexto);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido. Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transacao transacao = new Transacao(descricao, valor, categoria, entrada, LocalDate.now());
            DadosFinanceiros.adicionarTransacao(transacao);

            JOptionPane.showMessageDialog(this, "Transação cadastrada com sucesso!");

            campoDescricao.setText("");
            campoValor.setText("");
            comboCategoria.setSelectedIndex(0);
            checkEntrada.setSelected(false);
        });
    }
}

