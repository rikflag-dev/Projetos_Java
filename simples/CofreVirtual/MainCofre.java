package simples.CofreVirtual;

public class MainCofre {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new simples.CofreVirtual.view.CofreGUI().setVisible(true);
        });
    }
}
