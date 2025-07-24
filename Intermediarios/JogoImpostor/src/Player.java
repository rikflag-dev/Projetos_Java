package Intermediarios.JogoImpostor.src;

import java.awt.*;

public class Player {
    private String name;
    private Room room;

    public Player(String name, Room room) {
        this.name = name;
        this.room = room;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        int centerX = room.getX() + room.getWidth() / 2;
        int centerY = room.getY() + room.getHeight() / 2;
        g.fillOval(centerX - 10, centerY - 10, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString(name, centerX - 15, centerY - 15);
    }
}

