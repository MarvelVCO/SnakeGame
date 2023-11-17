package main;

import java.awt.*;

public class Fruit{
    Point point;

    public Fruit() {
        point = new Point(5, 5);
    }
    public boolean update(Point snakeHead) {
        if(snakeHead.equals(point)) {
            point = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
            return true;
        }
        return false;
    }
}
