package Intermediarios.JogoImpostor.src;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Among Us Offline");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        GamePanel gamePanel = new GamePanel();

        JScrollPane scrollPane = new JScrollPane(gamePanel);

        scrollPane.setPreferredSize(new Dimension(800, 600));

        this.add(scrollPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

