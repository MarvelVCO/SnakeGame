package main;

import java.awt.*;
import java.util.ArrayList;

public class Fruit{
    Point point;

    public Fruit() {
        point = new Point(5, 5);
    }
    public boolean update(ArrayList<Point> points) {
        if(points.get(points.size() - 1).equals(point)) {
            point = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
            for(int i = 0; i < points.size() - 1; i++) {
                if(point.equals(points.get(i))) {
                    point = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
                    i = 0;
                }
            }
            return true;
        }
        return false;
    }
}
