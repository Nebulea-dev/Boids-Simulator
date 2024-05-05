package main.gameBoards.bareBoard;

import java.awt.*;
import java.util.ArrayList;

/**
 * La classe Board décrit une plage de cellules
 */
public abstract class Board {

    /**
     * Largeur de la plage en nbre de cellules
     */
    private final Integer largeur;
    /**
     * Longueur de la plage en nbre de cellules
     */
    private final Integer longueur;
    /**
     * valeur par défault donnée aux cases du tableau
     */
    private final int defaultValue;
    /**
     * Tableau 2-dimensionel représentant les valeurs des cellules
     */
    public ArrayList<ArrayList<Integer>> board;
    /**
     * Tableau 2-dimensionel représentant les valeurs des cellules au prochain pas de temps
     */
    private ArrayList<ArrayList<Integer>> boardNextState;
    /**
     * Liste des cellules au début (état initial)
     */
    private final ArrayList<Cell> startingPoints;

    /**
     * <p> renvoie la largeur de la plage en nbre de cellules </p>
     * @return largeur de la plage en nbre de cellules
     */
    public Integer getLargeur() {
        return largeur;
    }

    /**
     * <p> renvoie la longiueur de la plage en nbre de cellules </p>
     * @return longueur de la plage en nbre de cellules
     */
    public Integer getLongueur() {
        return longueur;
    }

        /**
     * <p> renvoie la valeur d'une certaine cellule </p>
     * @param x coordinée en abcisse de la cellule visée
     * @param y coordinée en ordonnée de la cellule visée
     * @return valeur de la cellule visée
     */
    public int getStateOfCell(int x, int y){
        return board.get(x).get(y);
    }

    /**
     * <p> Constructeur de la classe </p>
     * @param largeur largeur de la plage en nbre de cellules
     * @param longueur longueur de la plage en nbre de cellules
     * @param startingPoints liste de cellules (doivent être dans la plage de cellules): ce sera la plage de valeurs initiales
     * @param defaultValue à préciser
     */
    public Board(Integer largeur,
                 Integer longueur,
                 ArrayList<Cell> startingPoints,
                 int defaultValue) {

        // Check type of points vs enum
        for(Cell cell: startingPoints)
        { if(cell.getX() < 0 || cell.getY() < 0)
        {
            throw new IllegalArgumentException("Starting point need to have a positive or null index");
        }

            if(cell.getX() >= largeur || cell.getY() >= longueur)
            {
                throw new IllegalArgumentException("Starting point need to have smaller than limit of boards index");
            }
        }

        this.largeur = largeur;
        this.longueur = longueur;

        this.board = new ArrayList<>();
        this.boardNextState = new ArrayList<>();

        this.startingPoints = startingPoints;
        this.defaultValue = defaultValue;

        setInitialState(startingPoints);
    }

    /**
     * <p> Génère/actualise le tableau des valeurs pour le prochain pas de temps </p>
     */
    public void generateNextState()
    {
        for(int i = 0; i < this.largeur; i++)
        {
            for(int j = 0; j < this.longueur; j++)
            {
                boardNextState.get(j).set(i, getNextStatus(new Point(i, j)));
            }
        }

        for(int i = 0; i < this.longueur; i++)
        {
            for(int j = 0; j < this.longueur; j++)
            {
                board.get(i).set(j, boardNextState.get(i).get(j));
            }
        }
    }

    /**
     * <p> Méthode abstraite, à définir dans une classe implémentant celle-ci </p>
     * @param point Cellule visée
     * @return Valeur de la cellule visée au prochain pas de temps
     */
    public abstract int getNextStatus(Point point);

    /**
     * <p> Définit l'état initial de la plage </p>
     * @param initialPoints Liste des cellules décrivant la plage avec les valeures initiales
     */
    public void setInitialState(ArrayList<Cell> initialPoints)
    {
        this.board = new ArrayList<>();
        this.boardNextState = new ArrayList<>();
        // Initial board is full of defaultValue
        for(int i = 0; i < longueur; i++)
        {
            this.board.add(new ArrayList<>());
            this.boardNextState.add(new ArrayList<>());
            for(int j = 0; j < largeur; j++)
            {
                this.board.get(i).add(defaultValue);
                this.boardNextState.get(i).add(defaultValue);
            }
        }

        for(Cell cell: initialPoints){
            board.get(cell.getY()).set(cell.getX(), cell.getCellValue());
        }
    }

    /**
     * <p> Réinitialise la plage </p>
     */
    public void restart(){
        setInitialState(this.startingPoints);
    }
}
