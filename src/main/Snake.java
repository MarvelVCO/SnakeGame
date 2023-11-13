package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends JPanel{
    final int tileSize = 32;
    int row;
    int column;
    KeyHandler kH = new KeyHandler();
    private ArrayList<Point> points = new ArrayList<Point>();

    public Snake(int row, int column) {
        this.row = row;
        this.column = column;
        this.addKeyListener(kH);
        this.setFocusable(true);
        points.add(new Point(10, 10));
    }

    public void drawSnake(Graphics g) {
        super.paintComponent(g);
//        Point point = new Point(x, y);
//        points.add(point);
        Graphics2D g2d = (Graphics2D) g;

        for(Point p : points) {
            g2d.fillRect(tileSize * (int) p.getX(), tileSize * (int) p.getY(), tileSize, tileSize);
        }

        g2d.setColor(Color.green);
        g2d.dispose();
    }

}
