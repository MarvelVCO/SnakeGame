package main;

import java.awt.*;
import java.util.ArrayList;

public class Fruit{
    Point point;
    public Fruit() {
        point = new Point(5, 5);
    }
    public boolean update(ArrayList<Point> points, String lastKeyPressed) {
        Point head = points.get(points.size() - 1);

        /* Uses the current direction the snake is facing to add size when the snake touches a block rather than when
        * it intersects with one*/
        if((head.getX() == point.getX() + 1 && lastKeyPressed.equals("a") && head.getY() == point.getY()) ||
                (head.getX() == point.getX() - 1 && lastKeyPressed.equals("d") && head.getY() == point.getY()) ||
                (head.getY() == point.getY() - 1 && lastKeyPressed.equals("s") && head.getX() == point.getX()) ||
                (head.getY() == point.getY() + 1 && lastKeyPressed.equals("w") && head.getX() == point.getX())) {
            point = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));

            for(int i = 0; i < points.size() - 1; i++) { // used to make sure fruits don't spawn inside the snake
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
