package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends JPanel{
    final ArrayList<Point> points = new ArrayList<>();

    public Snake() {
        points.add(new Point(8, 10));
        points.add(new Point(9, 10));
        points.add(new Point(10, 10));
    }

    public void update(String lastKeyPressed, boolean fruitEaten) {
        if(!fruitEaten) {
            points.remove(0);
        }
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
    }

    public boolean ripSnake() {
        if(points.get(points.size() - 1).getX() < 0 || points.get(points.size() - 1).getX() >= 20 ||
                points.get(points.size() - 1).getY() < 0 || points.get(points.size() - 1).getY() >= 20) {
            return true;
        }
        for(int i = 0; i < points.size() - 2; i ++) {
            if(points.get(i).equals(points.get(points.size() - 1))) {
                return true;
            }
        }
        return false;
    }
}
