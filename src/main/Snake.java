package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends JPanel{
    final int tileSize = 32;
    KeyHandler kH = new KeyHandler();
    private final ArrayList<Point> points = new ArrayList<Point>();

    public Snake() {
        this.addKeyListener(kH);
        this.setFocusable(true);
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
    public void update() {
        points.remove(0);
        System.out.println("aegvfguiaw");
        if(kH.upPressed) {
            System.out.println("up");
            points.add(new Point((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY() - 1));
        }
        if(kH.downPressed) {
            points.add(new Point((int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY() + 1));
        }
        if(kH.leftPressed) {
            points.add(new Point((int) points.get(points.size() - 1).getX() - 1, (int) points.get(points.size() - 1).getY()));
        }
        if(kH.rightPressed) {
            System.out.println("ebgouisebrg");
            points.add(new Point((int) points.get(points.size() - 1).getX() + 1, (int) points.get(points.size() - 1).getY()));
        }
//        if(points.get(points.size() - 1).getX() < 0 || points.get(points.size() - 1).getX() > 20 ||
//           points.get(points.size() - 1).getY() < 0 || points.get(points.size() - 1).getY() > 20) {
//            points.clear();
//        }
    }
}
