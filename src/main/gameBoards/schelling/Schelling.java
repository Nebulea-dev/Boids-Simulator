package main.gameBoards.schelling;

import main.gameBoards.bareBoard.Board;
import main.gameBoards.bareBoard.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
public class Schelling {

    private final Board board;

    private final ArrayList<Cell> emptyHouses;

    public int getLargeur()
    {
        return board.getLargeur();
    }
    public int getLongueur()
    {
        return board.getLongueur();
    }

    public int getStateOfCell(int x, int y)
    {
        return board.getStateOfCell(x, y);
    }

    /**
     * reinitialise le board
     * @param initialPoints Array de cell
     */
    public void setInitialState(ArrayList<Cell> initialPoints)
    {
        board.setInitialState(initialPoints);
    }

    public Schelling(
            int largeur,
            int longueur,
            ArrayList<Cell> startingPoints,
            int relocalisationThreshold) {

        this.emptyHouses = new ArrayList<>();

        // Initialize the list of empty houses
        for(Cell cell: startingPoints)
        {
            if(cell.getCellValue() == SchellingEnum.EMPTY_HOUSE.ordinal())
            {
                emptyHouses.add(cell);
            }
        }

        this.board = new Board(largeur, longueur, startingPoints, 0) {
            @Override
            public int getNextStatus(Point point) {
                return -1;
            }

            @Override
            public void generateNextState()
            {
                for(int i = 0; i < largeur; i++)
                {
                    for(int j = 0; j < longueur; j++)
                    {
                        if(doesCellNeedRelocalisation(i , j))
                        {
                            relocaliseCell(i, j);
                        }
                    }
                }
            }

            /**
             * cherche une nouvelle case libre de manière aléatoire et lui attribue la valeur de la case aux coordonnées x, y
             * @param x coorx de la cell
             * @param y coory de la cell
             */
            private void relocaliseCell(int x, int y)
            {
                int cellColor = board.get(y).get(x);
                board.get(y).set(x, SchellingEnum.EMPTY_HOUSE.ordinal());

                int randomEmptyHouseIndex = ThreadLocalRandom.current().nextInt(0, emptyHouses.size());
                Cell randomEmptyHouseCell = emptyHouses.get(randomEmptyHouseIndex);

                board.get(randomEmptyHouseCell.getY()).set(randomEmptyHouseCell.getX(), cellColor);

                emptyHouses.remove(randomEmptyHouseIndex);
                emptyHouses.add(new Cell(x, y, SchellingEnum.EMPTY_HOUSE.ordinal()));
            }

            /**
             * parcours les cases adjacentes et verifie le nombre de case de couleur différente
             * @param x coorx de la cell
             * @param y coory de la cell
             * @return true si on doit bouger
             */
            private boolean doesCellNeedRelocalisation(int x, int y)
            {
                int compteurDifferentColor = 0;
                int cellColor = board.get(y).get(x);

                for (int i = -1; i < 2; i++){
                    for (int j = -1; j < 2; j++){

                        // Ignore middle cell
                        if (i == 0 && j == 0){
                            continue;
                        }

                        // Stay inside the borders
                        if(y + i < 0 || y + i >= longueur)
                        {
                            continue;
                        }
                        if(x + j < 0 || x + j >= largeur)
                        {
                            continue;
                        }

                        int neighborColor = board.get(y + i).get(x + j);
                        if(neighborColor != cellColor && neighborColor != SchellingEnum.EMPTY_HOUSE.ordinal())
                        {
                            compteurDifferentColor += 1;
                        }

                        if(compteurDifferentColor >= relocalisationThreshold)
                        {
                            return true;
                        }
                    }
                }

                return false;
            }
        };
    }

    public void generateNextState()
    {
        board.generateNextState();
    }


    public void restart()
    {
        board.restart();
    }

}
