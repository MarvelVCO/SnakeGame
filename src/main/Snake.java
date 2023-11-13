package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends JPanel{
    final int tileSize = 32;
    int row;
    int column;
    KeyHandler kH = new KeyHandler();
    private ArrayList<Point> points;

    public Snake(int row, int column) {
        this.row = row;
        this.column = column;
        this.addKeyListener(kH);
        this.setFocusable(true);
        points = new ArrayList<Point>();
    }

    public void drawSnake(Graphics g) {
        super.paintComponent(g);
//        Point point = new Point(x, y);
//        points.add(point);
        Graphics2D g2d = (Graphics2D) g;
        for(Point p : points) {
            System.out.println(p.getX() + ", " + p.getY());
        }
        g2d.setColor(Color.green);

        g2d.fillRect(tileSize * 10, tileSize * 10, tileSize, tileSize);

        g2d.dispose();
    }

}
