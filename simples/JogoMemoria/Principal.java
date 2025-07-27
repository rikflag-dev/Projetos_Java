package simples.JogoMemoria;

import simples.JogoMemoria.view.Tabuleiro;

public class Principal {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Tabuleiro();
        });
    }
}
