package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;

public class Position /** This Class describe a specific Position in a Maze (row,column) */
{
    private int row;
    private int column;

    // We chose to describe illegal Position by {-1,-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

    /**
     * constructor
     * initializing a new Position, described by {row,column}
     *
     * @param row    The row index of the Position
     * @param column The column index of the Position
     */
    public Position(int row, int column) {
        if ((row < 0) || (column < 0)) {
            if (!((row == -1) && (column == -1)))
                throw new RuntimeException("One or more of the supplied sizes are not legal! Position can't have negative values");
        }
        this.row = row;
        this.column = column;
    }

    /**
     * @return The row index of the Position (int)
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return The column index of the Position (int)
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * @return The display of the Position - {row,column} (String)
     */
    public String toString() {
        return "{" + this.row + "," + this.column + "}";
    }


    /**
     * gets a Position, and returns all the Possible legal moves (Positions) from
     * the specific Position by the row and columns borders
     *
     * @param p          The Specific Position
     * @param max_row    The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return List of possible Positions (Position[])
     */
    public static Position[] findLegalNeighbors(Position p, int max_row, int max_column) // gets a Position, and returns all the Possible moves (Positions) in the Maze
    {
        if (p == null) {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if ((p.getRow() < 0) || (p.getColumn() < 0)) {
            if (!((p.getRow() == -1) && (p.getColumn() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }

        // for each possible move (up, down, left, right) we check if it's a valid move
        int NeighborsCounter = 0;
        Position[] poseArr = new Position[4];
        if (p.getRow() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(p.getRow() - 2, p.getColumn()); // upper
            NeighborsCounter++;
        }
        if (p.getRow() + 2 < max_row) {
            poseArr[NeighborsCounter] = new Position(p.getRow() + 2, p.getColumn()); // lower
            NeighborsCounter++;
        }
        if (p.getColumn() + 2 < max_column) {
            poseArr[NeighborsCounter] = new Position(p.getRow(), p.getColumn() + 2); // right
            NeighborsCounter++;
        }
        if (p.getColumn() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(p.getRow(), p.getColumn() - 2); // left
        }
        return poseArr;
    }
}