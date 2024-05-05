package test.gameBoards.schelling;

import main.gameBoards.bareBoard.Cell;
import main.gameBoards.schelling.Schelling;

import java.util.ArrayList;

public class TestSchelling {
    public static void main (String [] args) {

        ArrayList<Cell> startingPoints = new ArrayList<>();
        startingPoints.add(new Cell(3, 2, 3));
        startingPoints.add(new Cell(4, 2, 3));
        startingPoints.add(new Cell(5, 2, 3));

        Schelling schelling = new Schelling(10,10, startingPoints, 3);

        System.out.println(schelling);

        schelling.generateNextState();

        System.out.println(schelling);

        schelling.restart();

        System.out.println(schelling);

    }
}