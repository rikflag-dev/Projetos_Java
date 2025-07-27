package simples;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class GeradorPaletaCores extends JFrame {
    private static final int NUM_CORES = 5;
    private JPanel coresPanel;
    private JLabel[] colorLabels;
    private JTextField[] hexFields;
    private JButton gerarButton;

    public GeradorPaletaCores() {
        super("Gerador de Paletas de Cores Aleatórias");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        coresPanel = new JPanel();
        coresPanel.setLayout(new GridLayout(2, NUM_CORES, 10, 10));
        coresPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        colorLabels = new JLabel[NUM_CORES];
        hexFields = new JTextField[NUM_CORES];

        for (int i = 0; i < NUM_CORES; i++) {
            colorLabels[i] = new JLabel();
            colorLabels[i].setOpaque(true);
            colorLabels[i].setPreferredSize(new Dimension(100, 100));
            colorLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            coresPanel.add(colorLabels[i]);
        }

        for (int i = 0; i < NUM_CORES; i++) {
            hexFields[i] = new JTextField();
            hexFields[i].setHorizontalAlignment(JTextField.CENTER);
            hexFields[i].setEditable(false);
            hexFields[i].setFont(new Font("Monospaced", Font.BOLD, 16));
            hexFields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            int index = i;
            hexFields[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String hex = hexFields[index].getText();
                    copyToClipboard(hex);
                    JOptionPane.showMessageDialog(GeradorPaletaCores.this,
                        "Código " + hex + " copiado para a área de transferência!",
                        "Copiado", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            coresPanel.add(hexFields[i]);
        }

        gerarButton = new JButton("Gerar Nova Paleta");
        gerarButton.addActionListener(e -> gerarPaleta());

        add(coresPanel, BorderLayout.CENTER);
        add(gerarButton, BorderLayout.SOUTH);

        gerarPaleta();
    }

    private void gerarPaleta() {
        Random random = new Random();
        for (int i = 0; i < NUM_CORES; i++) {
            Color cor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            colorLabels[i].setBackground(cor);
            String hex = String.format("#%02X%02X%02X", cor.getRed(), cor.getGreen(), cor.getBlue());
            hexFields[i].setText(hex);
        }
    }

    private void copyToClipboard(String texto) {
        StringSelection stringSelection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeradorPaletaCores app = new GeradorPaletaCores();
            app.setVisible(true);
        });
    }
}

