package test.gameBoards.gameOfImmigration;

import main.gameBoards.bareBoard.Cell;
import main.gameBoards.gameOfImmigration.GameOfImmigration;

import java.util.ArrayList;

public class TestGameOfImmigration {
    public static void main (String [] args) {

        ArrayList<Cell> startingPoints = new ArrayList<>();
        startingPoints.add(new Cell(3, 2, 3));
        startingPoints.add(new Cell(4, 2, 3));
        startingPoints.add(new Cell(5, 2, 3));

        GameOfImmigration gameOfImmigration = new GameOfImmigration(10,10, startingPoints, 5);

        System.out.println(gameOfImmigration);

        gameOfImmigration.generateNextState();

        System.out.println(gameOfImmigration);

        gameOfImmigration.restart();

        System.out.println(gameOfImmigration);

    }
}