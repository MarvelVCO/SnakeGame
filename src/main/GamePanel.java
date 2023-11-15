package main;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{
    private final int tileSize = 32;
    private final int columns = 20;
    private final int rows = 20;
    private final int screenX = tileSize * columns;
    private final int screenY = tileSize * rows;
    private KeyHandler kH = new KeyHandler();
    private Thread gameThread;
    private Snake snake = new Snake();
    int FPS = 60;
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
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                snake.update();
                repaint();
                delta--;
                System.out.println(kH.downPressed);
            }
        }
    }
}
