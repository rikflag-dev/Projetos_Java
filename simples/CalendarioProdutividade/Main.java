package simples.CalendarioProdutividade;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarioFrame frame = new CalendarioFrame();
            frame.setVisible(true);
        });
    }
}

