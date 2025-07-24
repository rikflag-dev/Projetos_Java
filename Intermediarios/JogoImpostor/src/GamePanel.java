package Intermediarios.JogoImpostor.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private GameLogic logic;
    private JPanel votePanel;
    private JScrollPane scrollPane;

    public GamePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.black);

        logic = new GameLogic(this);
        logic.startGame();

        votePanel = new JPanel();
        votePanel.setLayout(new BoxLayout(votePanel, BoxLayout.Y_AXIS));
        votePanel.setPreferredSize(new Dimension(220, 600));
        votePanel.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(votePanel);
        scrollPane.setPreferredSize(new Dimension(220, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.EAST);

        // Clique no painel principal para eventuais ações
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() < getWidth() - scrollPane.getWidth()) {
                    logic.handleClick(e.getX(), e.getY());
                    repaint();
                }
            }
        });

        // Timer para atualizar o jogo e a tela a cada 1 segundo
        new Timer(1000, e -> {
            logic.updateGame();
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        logic.drawMainArea(g);
        if (logic.isVotingPhase()) {
            updateVotePanel();
        } else {
            votePanel.removeAll();
            votePanel.revalidate();
            votePanel.repaint();
        }
    }

    private void updateVotePanel() {
        votePanel.removeAll();
        java.util.List<NPC> npcs = logic.getNPCs();
        for (int i = 0; i < npcs.size(); i++) {
            NPC npc = npcs.get(i);
            JPanel npcPanel = new JPanel();
            npcPanel.setBackground(Color.GRAY);
            npcPanel.setMaximumSize(new Dimension(200, 50));
            npcPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

            // Círculo colorido
            JPanel colorCircle = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(npc.getColor());
                    g.fillOval(5, 5, 30, 30);
                }
            };
            colorCircle.setPreferredSize(new Dimension(40, 40));
            npcPanel.add(colorCircle);

            JLabel nameLabel = new JLabel(npc.getName());
            nameLabel.setForeground(Color.WHITE);
            npcPanel.add(nameLabel);

            final int index = i;
            npcPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    logic.handleVote(index);
                    repaint();
                }
            });

            votePanel.add(npcPanel);
        }
        votePanel.revalidate();
        votePanel.repaint();
    }
}


