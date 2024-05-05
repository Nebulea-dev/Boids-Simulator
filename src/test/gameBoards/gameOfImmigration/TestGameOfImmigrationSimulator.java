package test.gameBoards.gameOfImmigration;

import gui.GUISimulator;
import main.gameBoards.bareBoard.Cell;
import main.gameBoards.gameOfImmigration.GameOfImmigrationSimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestGameOfImmigrationSimulator {


    public static void main (String [] args) {
        GUISimulator window = new GUISimulator(500 , 500 , Color. BLACK);

        ArrayList<Cell> initialPoints = new ArrayList<>();

        for(int i = 5; i < 10; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                initialPoints.add(new Cell(i, j, 1));
            }
        }

        for(int i = 5; i < 10; i++)
        {
            for(int j = 5; j < 10; j++)
            {
                initialPoints.add(new Cell(i, j, 2));
            }
        }

        for(int i = 0; i < 5; i++)
        {
            for(int j = 5; j < 10; j++)
            {
                initialPoints.add(new Cell(i, j, 3));
            }
        }

        GameOfImmigrationSimulator gameOfLifeSimulator = new GameOfImmigrationSimulator(window, 10, 10, initialPoints, 4);

        window.setSimulable(gameOfLifeSimulator);
        gameOfLifeSimulator.drawState();
    }

}
