package main.gameBoards.gameOfLife;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import main.gameBoards.bareBoard.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe va instancier la classe GameOfLife et l'afficher. Cette classe implémente la classe Simulable
 */
public class GameOfLifeSimulator implements Simulable {

    /**
     * Liste de points qui décrivent les cellules vivantes à l'état initial
     */
    private final ArrayList<Cell> initialAlivePoints;

    /**
     * Fenêtre d'affichage
     */
    private final GUISimulator window;

    /**
     * Instance de la classe GameofLife ({@link GameOfLife.html}) qui s'occupe des calculs
     */
    private final GameOfLife gameoflife;

    /**
     * <p> Constructeur de la classe </p>
     * @param window Simulateur de l'interface graphique
     * @param largeur largeur du plateau de jeu voulue
     * @param longueur longueur du plateau de jeu voulue
     * @param initialAlivePoints ensemble des points en vie à l'initialisation
     */
    public GameOfLifeSimulator(GUISimulator window, int largeur, int longueur, ArrayList<Cell> initialAlivePoints) {
        this.window = window;
        this.initialAlivePoints = initialAlivePoints;
        this.gameoflife = new GameOfLife(
                largeur,
                longueur,
                initialAlivePoints
        );
    }

    /**
     * <p> Demande à la classe GameOfLife instanciée de calculer l'état du jeu pour le pas de temps suivant
     * et affiche le nouvel état. Fait appel à drawState() </p> 
     */
    @Override
    public void next() {
        gameoflife.generateNextState();
        this.drawState();
    }

    /**
     * <p> Affiche les cellules d'après l'état actuel. Efface les cellules ne correspondant plus à l'état actuel. </p> 
     */
    public void drawState()
    {
        for(int i = 0; i < gameoflife.getLargeur(); i++)
        {
            for(int j = 0; j < gameoflife.getLongueur(); j++)
            {
                int cellState = gameoflife.getStateOfCell(i, j);
                Color cellColor = (cellState == GameOfLifeEnum.ALIVE.ordinal() ? Color.BLUE : Color.GRAY);
                this.window.addGraphicalElement(
                        new Rectangle((i + 1) * 25, (j + 1)* 25, Color.WHITE, cellColor, 25)
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
        gameoflife.setInitialState(initialAlivePoints);
        this.drawState();
    }
}
