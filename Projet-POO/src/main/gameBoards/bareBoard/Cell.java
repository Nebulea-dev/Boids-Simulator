package main.gameBoards.bareBoard;

/**
 * La classe Cell définie une cellule par sa position et sa valeur.
 */
public class Cell {

    /**
     * Position en abcisse de la cellule
     */
    private final int x;
    /**
     * Position en ordonnée de la cellule
     */
    private final int y;
    /**
     * Valeur de la cellule
     */
    private final int cellValue;

    /**
     * <p> renvoie la coordonnée en abcisse de la cellule</p>
     * @return coordonnée en abcisse de la cellule
     */
    public int getX() {
        return x;
    }

    /**
     * <p> renvoie la coordonnée en ordonnée de la cellule</p>
     * @return coordonnée en ordonnée de la cellule
     */
    public int getY() {
        return y;
    }

    /**
     * <p> renvoie la valeur de la cellule</p>
     * @return valeur de la cellule
     */
    public int getCellValue() {
        return cellValue;
    }

    /**
     * <p> constructeur de la classe</p>
     * @param x coordonnée en abcisse de la cellule
     * @param y coordonnée en ordonnée de la cellule
     * @param value valeur de la cellule
     */
    public Cell(int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.cellValue = value;
    }
}
