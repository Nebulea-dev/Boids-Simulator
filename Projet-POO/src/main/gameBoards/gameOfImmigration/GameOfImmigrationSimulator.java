package main.gameBoards.gameOfImmigration;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import main.gameBoards.bareBoard.Cell;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Cette classe va instancier la classe GameOfImmigration et l'afficher. Cette classe implémente la classe Simulable
 */
public class GameOfImmigrationSimulator implements Simulable {

    private final GUISimulator window;
    private final GameOfImmigration gameOfImmigration;
    
    /**
     * Liste de points qui décrivent les cellules à l'état initial
     */
    private final ArrayList<Cell> initialPoints;
    private final int numOfCellState;


    /**
     * <p> Constructeur de la classe </p>
     * @param window Simulateur de l'interface graphique
     * @param largeur largeur du plateau de jeu voulue
     * @param longueur longueur du plateau de jeu voulue
     * @param initialPoints ensemble des cellules à l'initialisation
     * @param numOfCellState nombre d'états que les cellules peuvent prendre
     */
    public GameOfImmigrationSimulator(GUISimulator window, int largeur, int longueur, ArrayList<Cell> initialPoints, int numOfCellState) {
        this.window = window;

        this.initialPoints = initialPoints;
        this.numOfCellState = numOfCellState;

        this.gameOfImmigration = new GameOfImmigration(
                largeur,
                longueur,
                initialPoints,
                numOfCellState
        );
    }

    /**
     * <p> Demande à la classe GameOfImmigration instanciée de calculer l'état du jeu pour le pas de temps suivant
     * et affiche le nouvel état. Fait appel à drawState() </p> 
     */
    @Override
    public void next() {
        gameOfImmigration.generateNextState();
        this.drawState();
    }

    /**
     * <p> Affiche les cellules d'après l'état actuel. Efface les cellules ne correspondant plus à l'état actuel. </p> 
     */
    public void drawState()
    {
        for(int i = 0; i < gameOfImmigration.getLargeur(); i++)
        {
            for(int j = 0; j < gameOfImmigration.getLongueur(); j++)
            {
                int cellState = gameOfImmigration.getStateOfCell(i, j);
                Color cellColor = new Color(
                        20 + 235*cellState/ numOfCellState,
                        20 + 235*cellState/ numOfCellState,
                        20 + 235*cellState/ numOfCellState);

                this.window.addGraphicalElement(
                        new Rectangle((i +1) * 25, (j + 1) * 25, Color.WHITE, cellColor, 25)
                );
            }
        }
    }

    /**
     * <p> Réinitialise le jeu en appelant la méthode setInitialState de la classe gameOfLife avec l'attribut initialAlivePoints.
     * Affiche l'état initial. </p>
     */
    @Override
    public void restart() {
        gameOfImmigration.setInitialState(initialPoints);
        this.drawState();
    }
}
