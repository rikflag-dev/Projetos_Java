package Intermediarios.Financas.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Intermediarios.Financas.Dados.DadosFinanceiros;
import Intermediarios.Financas.Modelo.Transacao;

public class PainelRelatorios extends JPanel {

    private GraficoPizza graficoPizza;
    private JButton botaoAtualizar;

    public PainelRelatorios() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JLabel titulo = new JLabel("Relatório Mensal de Gastos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        graficoPizza = new GraficoPizza();
        graficoPizza.setPreferredSize(new Dimension(800, 600));
        JScrollPane scrollPane = new JScrollPane(graficoPizza);
        scrollPane.setBackground(new Color(30, 30, 30));
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        botaoAtualizar = new JButton("Atualizar Relatório");
        add(botaoAtualizar, BorderLayout.SOUTH);

        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarRelatorio();
            }
        });

        atualizarRelatorio();
    }

    private void atualizarRelatorio() {
        List<Transacao> transacoes = DadosFinanceiros.getTransacoes();

        List<Transacao> despesas = transacoes.stream()
                .filter(t -> !t.isEntrada())
                .collect(Collectors.toList());

        if (despesas.isEmpty()) {
            graficoPizza.setDados(null);
            graficoPizza.repaint();
            JOptionPane.showMessageDialog(this, "Nenhuma despesa cadastrada para mostrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Map<String, Double> totalPorCategoria = despesas.stream()
                .collect(Collectors.groupingBy(
                        Transacao::getCategoria,
                        Collectors.summingDouble(Transacao::getValor)
                ));

        graficoPizza.setDados(totalPorCategoria);
        graficoPizza.repaint();

        verificarEconomiaExtrema(despesas);
    }

    private void verificarEconomiaExtrema(List<Transacao> despesas) {
        double totalDespesas = despesas.stream()
                .mapToDouble(Transacao::getValor)
                .sum();

        double totalEntradas = DadosFinanceiros.getTransacoes().stream()
                .filter(Transacao::isEntrada)
                .mapToDouble(Transacao::getValor)
                .sum();

        if (totalEntradas > 0 && totalDespesas > totalEntradas * 0.7) {
            JOptionPane.showMessageDialog(this,
                    "Alerta: Seus gastos já ultrapassaram 70% dos seus ganhos. Modo Economia Extrema ativado!",
                    "Aviso de Economia Extrema",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    class GraficoPizza extends JPanel {
        private Map<String, Double> dados;
        private final Color[] cores = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK
        };

        public void setDados(Map<String, Double> dados) {
            this.dados = dados;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(40, 40, 40));
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (dados == null || dados.isEmpty()) {
                return;
            }

            int largura = getWidth();
            int altura = getHeight();
            int tamanho = Math.min(largura, altura) - 150;
            int x = (largura - tamanho) / 2;
            int y = 50;

            double total = dados.values().stream().mapToDouble(Double::doubleValue).sum();
            double anguloInicial = 0.0;
            int corIndex = 0;

            for (Map.Entry<String, Double> entrada : dados.entrySet()) {
                double valor = entrada.getValue();
                double angulo = valor / total * 360;

                g2.setColor(cores[corIndex % cores.length]);
                g2.fillArc(x, y, tamanho, tamanho, (int) anguloInicial, (int) Math.ceil(angulo));
                anguloInicial += angulo;
                corIndex++;
            }

            int legendaY = y + tamanho + 20;
            int legendaX = 50;
            corIndex = 0;

            for (Map.Entry<String, Double> entrada : dados.entrySet()) {
                g2.setColor(cores[corIndex % cores.length]);
                g2.fillRect(legendaX, legendaY, 20, 20);
                g2.setColor(Color.WHITE);
                g2.drawString(entrada.getKey() + " - R$ " + String.format("%.2f", entrada.getValue()), legendaX + 25, legendaY + 15);
                legendaY += 25;
                corIndex++;
            }
        }
    }
}
