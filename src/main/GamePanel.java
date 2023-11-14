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
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
