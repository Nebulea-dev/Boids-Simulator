package test.gameBoards.gameoflife;

import gui.GUISimulator;
import main.gameBoards.bareBoard.Cell;
import main.gameBoards.gameOfLife.GameOfLifeEnum;
import main.gameBoards.gameOfLife.GameOfLifeSimulator;

import java.awt.Color;
import java.util.ArrayList;

public class TestGameOfLifeSimulator {


    public static void main (String [] args) {
        GUISimulator window = new GUISimulator(500 , 500 , Color. BLACK);

        ArrayList<Cell> initialAlivePoints = new ArrayList<>();
        initialAlivePoints.add(new Cell(3, 2, GameOfLifeEnum.ALIVE.ordinal()));
        initialAlivePoints.add(new Cell(4, 2, GameOfLifeEnum.ALIVE.ordinal()));
        initialAlivePoints.add(new Cell(5, 2, GameOfLifeEnum.ALIVE.ordinal()));

        initialAlivePoints.add(new Cell(9, 9, GameOfLifeEnum.ALIVE.ordinal()));
        initialAlivePoints.add(new Cell(8, 9, GameOfLifeEnum.ALIVE.ordinal()));
        initialAlivePoints.add(new Cell(7, 9, GameOfLifeEnum.ALIVE.ordinal()));

        GameOfLifeSimulator gameOfLifeSimulator = new GameOfLifeSimulator(window, 10, 10, initialAlivePoints);

        window.setSimulable(gameOfLifeSimulator);
        gameOfLifeSimulator.drawState();
    }

}
