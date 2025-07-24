package Intermediarios.JogoImpostor.src;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class NPC {
    private String name;
    private Room room;
    private Color color;
    private Random random = new Random();

    public NPC(String name, Room room) {
        this.name = name;
        this.room = room;
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public void moveRandomly(List<Room> rooms) {
        // Move para uma sala adjacente aleatória (simplificado)
        if (random.nextInt(10) < 2) { // 20% chance de mudar de sala a cada update
            room = rooms.get(random.nextInt(rooms.size()));
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int centerX = room.getX() + room.getWidth() / 2;
        int centerY = room.getY() + room.getHeight() / 2;
        g.fillOval(centerX - 15, centerY - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(name, centerX - 15, centerY + 30);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
