package main;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable{
    private final int tileSize = 32;
    private final KeyHandler kH = new KeyHandler();
    private String lastKeyPressed;
    private Thread gameThread;
    private Snake snake = new Snake();
    private final Fruit fruit = new Fruit();
    private boolean inMenu;
    private boolean fruitEaten;
    int FPS = 10;
    public GamePanel() {
        int rows = 20;
        int screenY = tileSize * rows;
        int columns = 20;
        int screenX = tileSize * columns;
        this.inMenu = true;
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
            if(inMenu) {
                if(kH.keyPressed) {
                    inMenu = false;
                }
            }
            else {
                if (kH.upPressed && !lastKeyPressed.equals("s") &&
                        snake.points.get(snake.points.size() - 1).getY() - 1 != snake.points.get(snake.points.size() - 2).getY()) {
                    lastKeyPressed = "w";
                }
                if (kH.downPressed && !lastKeyPressed.equals("w") &&
                        snake.points.get(snake.points.size() - 1).getY() + 1 != snake.points.get(snake.points.size() - 2).getY()) {
                    lastKeyPressed = "s";
                }
                if (kH.leftPressed && !lastKeyPressed.equals("d") &&
                        snake.points.get(snake.points.size() - 1).getX() - 1 != snake.points.get(snake.points.size() - 2).getX()) {
                    lastKeyPressed = "a";
                }
                if (kH.rightPressed && !lastKeyPressed.equals("a") &&
                        snake.points.get(snake.points.size() - 1).getX() + 1 != snake.points.get(snake.points.size() - 2).getX()) {
                    lastKeyPressed = "d";
                }
            }
            if(delta >= 1) {
                fruitEaten = fruit.update(snake.points.get(snake.points.size() - 1));
                System.out.println(inMenu + "  " + kH.keyPressed);
                if(snake.update(lastKeyPressed, fruitEaten)) {
                    snake = new Snake();
                    inMenu = true;
                }

                repaint();
                delta--;
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inMenu) {
            Font font = new Font("Verdana", Font.BOLD, 23);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("WASD to move, click any key to start", 75, 75);
        }
        else {
            Graphics2D fruitGraphics = (Graphics2D) g;
            fruitGraphics.setColor(Color.red);
            fruitGraphics.fillRect(tileSize * (int) fruit.point.getX(), tileSize * (int) fruit.point.getY(), tileSize, tileSize);

            Graphics2D snakeGraphics = (Graphics2D) g;
            snakeGraphics.setColor(Color.green);
            for (Point p : snake.points) {
                snakeGraphics.fillRect(tileSize * (int) p.getX(), tileSize * (int) p.getY(), tileSize, tileSize);
            }


            snakeGraphics.dispose();
            fruitGraphics.dispose();
        }
    }
}
