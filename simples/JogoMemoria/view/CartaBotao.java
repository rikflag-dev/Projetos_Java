package simples.JogoMemoria.view;

import java.awt.Font;
import javax.swing.JButton;

public class CartaBotao extends JButton {
    private final int valor;
    private boolean encontrada = false;

    public CartaBotao(int valor) {
        this.valor = valor;
        setFont(new Font("Arial", Font.BOLD, 22));
        setText("?");
    }

    public void mostrarValor() {
        setText(String.valueOf(valor));
    }

    public void esconderValor() {
        setText("?");
    }

    public int getValor() {
        return valor;
    }

    public boolean foiEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
}

