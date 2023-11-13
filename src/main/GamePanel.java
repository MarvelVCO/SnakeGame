package main;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{
    final int tileSize = 32;
    final int columns = 20;
    final int rows = 20;
    final int screenX = tileSize * columns;
    final int screenY = tileSize * rows;
    KeyHandler kH = new KeyHandler();
    Thread gameThread;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenX, screenY));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {

        }
    }

    public void update() {
        update();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.green);

        g2d.fillRect(tileSize * 10, tileSize * 10, tileSize, tileSize);

        g2d.dispose();
    }
}
