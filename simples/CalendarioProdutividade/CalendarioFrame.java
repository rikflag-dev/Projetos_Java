package simples.CalendarioProdutividade;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarioFrame extends JFrame {
    private Map<Integer, List<Tarefa>> calendario = new HashMap<>();
    private JPanel diasPanel;
    private JTextArea tarefasArea;

    public CalendarioFrame() {
        setTitle("Calendário da Produtividade");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        diasPanel = new JPanel(new GridLayout(5, 7, 5, 5));
        tarefasArea = new JTextArea(5, 20);
        tarefasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tarefasArea);

        atualizarCalendario();

        add(diasPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void atualizarCalendario() {
        diasPanel.removeAll();
        for (int dia = 1; dia <= 31; dia++) {
            JButton diaBtn = new JButton(String.valueOf(dia));
            diaBtn.setBackground(Color.LIGHT_GRAY);

            if (calendario.containsKey(dia)) {
                for (Tarefa t : calendario.get(dia)) {
                    if (t.getTipo() == TarefaTipo.ESTUDO)
                        diaBtn.setBackground(Color.CYAN);
                    else if (t.getTipo() == TarefaTipo.TRABALHO)
                        diaBtn.setBackground(Color.ORANGE);
                    else if (t.getTipo() == TarefaTipo.LAZER)
                        diaBtn.setBackground(Color.PINK);
                }
            }

            final int diaSelecionado = dia;
            diaBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarTarefasDoDia(diaSelecionado);
                    adicionarTarefa(diaSelecionado);
                }
            });

            diasPanel.add(diaBtn);
        }
        diasPanel.revalidate();
        diasPanel.repaint();
    }

    private void mostrarTarefasDoDia(int dia) {
        StringBuilder texto = new StringBuilder("Tarefas do dia " + dia + ":\n");

        List<Tarefa> tarefasDoDia = calendario.get(dia);
        if (tarefasDoDia == null || tarefasDoDia.isEmpty()) {
            texto.append("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa t : tarefasDoDia) {
                texto.append("- [").append(t.getTipo()).append("] ").append(t.getDescricao()).append("\n");
            }
        }

        tarefasArea.setText(texto.toString());
    }

    private void adicionarTarefa(int dia) {
        String descricao = JOptionPane.showInputDialog(this, "Descrição da tarefa:");
        if (descricao == null || descricao.trim().isEmpty()) return;

        Object[] opcoes = TarefaTipo.values();
        TarefaTipo tipo = (TarefaTipo) JOptionPane.showInputDialog(
            this,
            "Selecione o tipo:",
            "Tipo da Tarefa",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            TarefaTipo.ESTUDO
        );

        if (tipo != null) {
            Tarefa nova = new Tarefa(descricao, tipo);
            calendario.computeIfAbsent(dia, k -> new ArrayList<>()).add(nova);
            JOptionPane.showMessageDialog(this, "Tarefa adicionada com sucesso!");
            atualizarCalendario();
            mostrarTarefasDoDia(dia);
        }
    }
}
