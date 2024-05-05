package test.gameBoards.gameoflife;

import main.gameBoards.bareBoard.Cell;
import main.gameBoards.gameOfLife.GameOfLife;
import main.gameBoards.gameOfLife.GameOfLifeEnum;

import java.util.ArrayList;

public class TestGameOfLife {
    public static void main (String [] args) {

        ArrayList<Cell> startingPoints = new ArrayList<>();
        startingPoints.add(new Cell(3, 2, GameOfLifeEnum.ALIVE.ordinal()));
        startingPoints.add(new Cell(4, 2, GameOfLifeEnum.ALIVE.ordinal()));
        startingPoints.add(new Cell(5, 2, GameOfLifeEnum.ALIVE.ordinal()));

        GameOfLife gameoflife = new GameOfLife(10,10, startingPoints);

        System.out.println(gameoflife);

        gameoflife.generateNextState();

        System.out.println(gameoflife);

        gameoflife.restart();

        System.out.println(gameoflife);

    }
}