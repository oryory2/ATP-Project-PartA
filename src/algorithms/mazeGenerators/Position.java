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
}