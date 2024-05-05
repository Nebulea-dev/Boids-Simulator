package main.gameBoards.schelling;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import main.gameBoards.bareBoard.Cell;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Integer.max;

public class SchellingSimulator implements Simulable {

    private final GUISimulator window;
    private final Schelling schelling;
    private final ArrayList<Cell> initialPoints;

    private int numOfColors;


    public SchellingSimulator(GUISimulator window, int largeur, int longueur, ArrayList<Cell> initialPoints, int relocalisationThreshold) {
        this.window = window;

        this.initialPoints = initialPoints;

        // Get the num of colors, only used for the drawing colors
        numOfColors = 1;
        for(Cell cell : initialPoints)
        {
            numOfColors = max(cell.getCellValue(), numOfColors);
        }

        this.schelling = new Schelling(
                largeur,
                longueur,
                initialPoints,
                relocalisationThreshold
        );
    }

    @Override
    public void next() {
        schelling.generateNextState();
        this.drawState();
    }

    public void drawState()
    {
        int SIZE_OF_SQUARE = 10;
        for(int i = 0; i < schelling.getLargeur(); i++)
        {
            for(int j = 0; j < schelling.getLongueur(); j++)
            {
                int cellState = schelling.getStateOfCell(i, j);
                Color cellColor = new Color(
                        20 + 235*cellState/ numOfColors,
                        20 + 235*cellState/ numOfColors,
                        20 + 235*cellState/ numOfColors);
                this.window.addGraphicalElement(
                        new Rectangle((i +1) * SIZE_OF_SQUARE, (j + 1) * SIZE_OF_SQUARE, Color.WHITE, cellColor, SIZE_OF_SQUARE)
                );
            }
        }
    }

    @Override
    public void restart() {
        schelling.setInitialState(initialPoints);
        this.drawState();
    }
}
