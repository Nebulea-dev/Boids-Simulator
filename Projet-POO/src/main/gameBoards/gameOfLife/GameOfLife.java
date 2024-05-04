package main.gameBoards.gameOfLife;

import main.gameBoards.bareBoard.Board;
import main.gameBoards.bareBoard.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe implémente le jeu de la vie d'après Conway
 */
public class GameOfLife {
    /**
     * "plateau de jeu"
     */
    private final Board board;

    /**
     * <p> Constructeur </p>
     * @param largeur largeur du plateau de jeu en nbre de cellules
     * @param longueur longueur du plateau de jeu en nbre de cellules
     * @param startingPoints cellules dans le plateau de jeu avec état vivant à l'initial
     */
    public GameOfLife(Integer largeur, Integer longueur, ArrayList<Cell> startingPoints) {
        this.board = new Board(largeur, longueur, startingPoints, GameOfLifeEnum.DEAD.ordinal()) {
            @Override
            public int getNextStatus(Point point) {
                int cptAlive = 0;
                for (int i = -1; i <= 1; i++){
                    for (int j = -1; j <= 1; j++){

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

                        if (board.get(point.y + i).get(point.x + j) == GameOfLifeEnum.ALIVE.ordinal()){
                            cptAlive++;
                        }
                    }
                }
                if (board.get(point.y).get(point.x) == GameOfLifeEnum.ALIVE.ordinal()){
                    if (cptAlive == 2 || cptAlive == 3){
                        return GameOfLifeEnum.ALIVE.ordinal();
                    }
                    else {
                        return GameOfLifeEnum.DEAD.ordinal();
                    }
                }
                if (board.get(point.y).get(point.x) == GameOfLifeEnum.DEAD.ordinal()){
                    if (cptAlive == 3){
                        return GameOfLifeEnum.ALIVE.ordinal();
                    }
                }
                return board.get(point.y).get(point.x);
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

    /**
     * <p> Renvoie un String décrivant l'état du plateau actuel </p>
     * @return String décrivant l'état du plateau actuel
     */
    @Override
    public String toString() {
        StringBuilder gameOfLifeBoardString = new StringBuilder();

        gameOfLifeBoardString.append("-".repeat(5 * board.getLongueur() + 1));
        gameOfLifeBoardString.append("\n");
        for(int i = 0; i < board.getLargeur(); i++)
        {
            gameOfLifeBoardString.append("|");
            for(int j = 0; j < board.getLongueur(); j++)
            {
                gameOfLifeBoardString.append(board.board.get(i).get(j) == GameOfLifeEnum.ALIVE.ordinal() ? " \uD83D\uDFE6 " : " \uD83D\uDFE5 ");
                gameOfLifeBoardString.append("|");
            }

            gameOfLifeBoardString.append("\n");

            gameOfLifeBoardString.append("-".repeat(5 * board.getLongueur() + 1));
            gameOfLifeBoardString.append("\n");
        }

        return gameOfLifeBoardString.toString();
    }
}
