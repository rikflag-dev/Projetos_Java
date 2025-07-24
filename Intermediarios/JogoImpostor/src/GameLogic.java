package Intermediarios.JogoImpostor.src;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameLogic {
    private List<NPC> npcs;
    private List<Room> rooms;
    private Player player;
    private int impostorIndex;
    private GamePanel panel;
    private boolean votingPhase = false;
    private boolean gameOver = false;
    private int timerSeconds = 10;

    public GameLogic(GamePanel panel) {
        this.panel = panel;
    }

    public void startGame() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Refeitório", 50, 50, 200, 150));
        rooms.add(new Room("Admin", 300, 50, 200, 150));
        rooms.add(new Room("Elétrica", 50, 250, 200, 150));
        rooms.add(new Room("Navegação", 300, 250, 200, 150));

        npcs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Room startRoom = rooms.get(i % rooms.size());
            npcs.add(new NPC("Bot " + (i + 1), startRoom));
        }

        player = new Player("Você", rooms.get(0));
        impostorIndex = new Random().nextInt(npcs.size());
    }

    public void updateGame() {
        if (gameOver) return;

        timerSeconds--;
        if (timerSeconds <= 0) {
            if (!votingPhase) {
                votingPhase = true;
                timerSeconds = 15; // tempo para votação
            } else {
                gameOver = true;
            }
        }

        if (!votingPhase) {
            for (NPC npc : npcs) {
                npc.moveRandomly(rooms);
            }
        }
    }

    public void drawMainArea(Graphics g) {
        // Desenha salas
        for (Room room : rooms) {
            room.draw(g);
        }
        // Desenha player
        player.draw(g);
        // Desenha NPCs
        for (NPC npc : npcs) {
            npc.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Tempo: " + timerSeconds + "s", 600, 30);

        if (votingPhase && !gameOver) {
            g.drawString("FASE DE VOTAÇÃO - Clique no bot à direita para votar!", 200, 550);
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("JOGO TERMINADO!", 350, 50);
        }
    }

    public void handleClick(int x, int y) {
        // Se quiser implementar algo no futuro
    }

    public void handleVote(int npcIndex) {
        if (!votingPhase || gameOver) return;

        if (npcIndex == impostorIndex) {
            System.out.println("Você acertou! Era o " + npcs.get(npcIndex).getName());
        } else {
            System.out.println("Errado! " + npcs.get(npcIndex).getName() + " era inocente.");
        }

        gameOver = true;
        votingPhase = false;
    }

    public boolean isVotingPhase() {
        return votingPhase;
    }

    public List<NPC> getNPCs() {
        return npcs;
    }
}

