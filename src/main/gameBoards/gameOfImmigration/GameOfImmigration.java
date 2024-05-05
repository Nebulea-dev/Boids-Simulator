package main.gameBoards.gameOfImmigration;

import main.gameBoards.bareBoard.Board;
import main.gameBoards.bareBoard.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe implémente le jeu de l'immigration.
 */
public class GameOfImmigration {

    /**
     * "plateau de jeu"
     */
    private final Board board;

    /**
     * <p> Constructeur </p>
     * @param largeur largeur du plateau de jeu en nbre de cellules
     * @param longueur longueur du plateau de jeu en nbre de cellules
     * @param startingPoints cellules dans le plateau de jeu à l'état initial
     * @param numOfCellStates nombre d'états que les cellules peuvent avoir 
     */
    public GameOfImmigration(
            int largeur,
            int longueur,
            ArrayList<Cell> startingPoints,
            int numOfCellStates) {

        this.board = new Board(largeur, longueur, startingPoints, 0) {
            @Override
            public int getNextStatus(Point point) {
                int compteurState = 0;
                int cellState = board.get(point.y).get(point.x);
                int cellStatePlusOne = (cellState + 1) % numOfCellStates;

                for (int i = -1; i < 2; i++){
                    for (int j = -1; j < 2; j++){

                        // Ignore middle cell
                        if (i == 0 && j == 0){
                            continue;
                        }

                        // Stay inside the borders
                        if(point.y + i < 0 || point.y + i >= longueur)
                        {
                            continue;
                        }
                        if(point.x + j < 0 || point.x + j >= largeur)
                        {
                            continue;
                        }

                        int neighborState = board.get(point.y + i).get(point.x + j);
                        if(neighborState == cellStatePlusOne)
                        {
                            compteurState += 1;
                        }
                    }
                }

                if(compteurState >= 3)
                {
                    return cellStatePlusOne;
                }
                else
                {
                    return cellState;
                }
            }
        };
    }

    /**
     * <p> Génère le tableau avec les états du prochain pas de temps </p>
     */
    public void generateNextState()
    {
        board.generateNextState();
    }

    /**
     * <p> Renvoie la largeur du plateau de jeu </p>
     * @return largeur du plateau de jeu en nbre de cellules
     */
    public int getLargeur()
    {
        return board.getLargeur();
    }

    /**
     * <p> Renvoie la longueur du plateau de jeu </p>
     * @return longueur du plateau de jeu en nbre de cellules
     */
    public int getLongueur()
    {
        return board.getLongueur();
    }

    /**
     * <p> Renvoie l'état d'une cellule du plateau de jeu </p>
     * @param x coordinée en abcisse de la cellule visée
     * @param y coordinée en ordonnée de la cellule visée
     * @return valeur de la cellule visée
     */
    public int getStateOfCell(int x, int y)
    {
        return board.getStateOfCell(x, y);
    }

    /**
     * <p> Définit l'état initial du plateau </p>
     * @param initialPoints Liste des cellules décrivant le plateau avec les états initials
     */
    public void setInitialState(ArrayList<Cell> initialPoints)
    {
        board.setInitialState(initialPoints);
    }

    /**
     * <p> Réinitialise le plateau </p>
     */
    public void restart()
    {
        board.restart();
    }
}
