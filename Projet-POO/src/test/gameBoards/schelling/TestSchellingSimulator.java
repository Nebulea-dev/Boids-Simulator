package test.gameBoards.schelling;

import gui.GUISimulator;
import main.gameBoards.bareBoard.Cell;
import main.gameBoards.schelling.SchellingSimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TestSchellingSimulator {


    public static void main (String [] args) {
        GUISimulator window = new GUISimulator(500 , 500 , Color. BLACK);

        ArrayList<Cell> initialPoints = new ArrayList<>();

        int NUM_OF_COLORS = 3;

        for(int i = 0; i < 50; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                int randomColor = ThreadLocalRandom.current().nextInt(0, NUM_OF_COLORS + 1);
                initialPoints.add(new Cell(i, j, randomColor));
            }
        }

        SchellingSimulator schellingSimulator = new SchellingSimulator(window, 50, 50, initialPoints, 3);

        window.setSimulable(schellingSimulator);
        schellingSimulator.drawState();
    }

}
