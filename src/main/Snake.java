package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends JPanel{
    private final int tileSize = 32;
    private KeyHandler kH = new KeyHandler();
    private Thread snakeThread;
    final ArrayList<Point> points = new ArrayList<Point>();

    public Snake() {
        this.addKeyListener(kH);
        this.setFocusable(true);
        points.add(new Point(8, 10));
        points.add(new Point(9, 10));
        points.add(new Point(10, 10));
    }

    public void drawSnake(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

                for(Point p : points) {
                    g2d.fillRect(tileSize * (int) p.getX(), tileSize * (int) p.getY(), tileSize, tileSize);
                }

        g2d.setColor(Color.green);
        g2d.dispose();
    }
    public void update(String lastKeyPressed) {
        points.remove(0);
        if(lastKeyPressed.equals("w")) {
            points.add(new Point((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY() - 1));
        }
        if(lastKeyPressed.equals("s")) {
            points.add(new Point((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY() + 1));
        }
        if(lastKeyPressed.equals("a")) {
            points.add(new Point((int) points.get(points.size() - 1).getX() - 1, (int) points.get(points.size() - 1).getY()));
        }
        if(lastKeyPressed.equals("d")) {
            points.add(new Point((int) points.get(points.size() - 1).getX() + 1, (int) points.get(points.size() - 1).getY()));
        }
        if(points.get(0).getX() < 0 || points.get(0).getX() > 20 ||
           points.get(0).getY() < 0 || points.get(0).getY() > 20) {
            points.clear();
        }
    }
}
