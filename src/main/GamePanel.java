package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    private final int tileSize = 32;
    private final KeyHandler kH = new KeyHandler();
    private String lastKeyPressed;
    private Thread gameThread;
    private Snake snake = new Snake();
    private final ArrayList<Color> colors = new ArrayList<>();
    private final Fruit fruit = new Fruit();
    private boolean inMenu;
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
        colors.add(new Color(22, 200, 58));
        colors.add(new Color(13, 153, 168));
        colors.add(new Color(41, 13, 200));
        colors.add(new Color(150, 13, 168));
        colors.add(new Color(168, 13, 85));
        colors.add(new Color(200, 21, 13));
        colors.add(new Color(243, 255, 20));

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
                if(kH.anyKeyPressed) {
                    inMenu = false;
                    snake = new Snake();
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
                boolean fruitEaten = fruit.update(snake.points, lastKeyPressed);
                if(snake.ripSnake()) {
                    kH.anyKeyPressed = false;
                    inMenu = true;
                }
                else if(!inMenu) {
                    snake.update(lastKeyPressed, fruitEaten);
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
            Graphics2D snakeGraphics = (Graphics2D) g;
            int currentColor = 0;
            snakeGraphics.setColor(colors.get(currentColor));
            for(int i = 0; i < snake.points.size(); i++) {
                if(currentColor >= colors.size() - 1) {
                    currentColor = 0;
                }
                else {
                    currentColor++;
                }
                snakeGraphics.setColor(colors.get(currentColor - 1 == -1 ? colors.size() - 1 : currentColor - 1));
                snakeGraphics.fillRect(tileSize * (int) snake.points.get(i).getX(), tileSize * (int) snake.points.get(i).getY(), tileSize, tileSize);

            }

            Graphics2D fruitGraphics = (Graphics2D) g;
            fruitGraphics.setColor(colors.get(currentColor));
            fruitGraphics.fillRect(tileSize * (int) fruit.point.getX(), tileSize * (int) fruit.point.getY(), tileSize, tileSize);
            Font font = new Font("Verdana", Font.BOLD, 20);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(String.valueOf(snake.points.size() - 3), 600, 40);

            snakeGraphics.dispose();
            fruitGraphics.dispose();
        }
    }
}
