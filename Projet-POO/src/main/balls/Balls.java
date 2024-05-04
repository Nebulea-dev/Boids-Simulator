package main.balls;

import java.util.*;
import java.util.List;

public class Balls implements Iterable<Ball>{

    private List<Ball> list = new ArrayList<>();

    public List<Ball> getList() {
        return list;
    }

    public void setList(List<Ball> list) {
        this.list = list;
    }

    @Override
    public Iterator<Ball> iterator() {
        return list.iterator();
    }

    /**
     * translation de chaque balle dans list par le vecteur (dx, dy)
     * @param dx translation sur l'axe x
     * @param dy translation sur l'axe y
     */
    public void translate(int dx, int dy) {
        for (Ball Ball: list) {
            Ball.translate(dx, dy);
        }
    }

    /**
     * replace chaque balle à sa position initial
     */
    public void reInit() {
        for (Ball ball: list) {
            ball.setLocation(ball.getDefaultX(), ball.getDefaultY());
        }
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Balls{");

        for (int i = 0; i < list.size() - 1; i++) {
            Ball Ball = list.get(i);
            listString.append(Ball.toString());
            listString.append(", ");
        }

        listString.append(list.get(list.size() - 1));

        return listString.append("}").toString();
    }
}

