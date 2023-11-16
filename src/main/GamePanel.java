package main;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{
    private final int tileSize = 32;
    private final KeyHandler kH = new KeyHandler();
    private String lastKeyPressed;
    private Thread gameThread;
    private final Snake snake = new Snake();
    int FPS = 10;
    public GamePanel() {
        int rows = 20;
        int screenY = tileSize * rows;
        int columns = 20;
        int screenX = tileSize * columns;
        this.setPreferredSize(new Dimension(screenX, screenY));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
        lastKeyPressed = "d";
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                snake.update(lastKeyPressed);
                repaint();
                delta--;
                if(kH.upPressed && !lastKeyPressed.equals("s")) {
                    lastKeyPressed = "w";
                }
                if(kH.downPressed && !lastKeyPressed.equals("w")) {
                    lastKeyPressed = "s";
                }
                if(kH.leftPressed && !lastKeyPressed.equals("d")) {
                    lastKeyPressed = "a";
                }
                if(kH.rightPressed && !lastKeyPressed.equals("a")) {
                    lastKeyPressed = "d";
                }
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.green);

        for(Point p : snake.points) {
            g2d.fillRect(tileSize * (int) p.getX(), tileSize * (int) p.getY(), tileSize, tileSize);
        }

        g2d.dispose();
    }
}
