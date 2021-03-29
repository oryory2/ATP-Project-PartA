package algorithms.mazeGenerators;

import java.util.List;

public class Position
        /**
         * This Class describe a specific Position in a Maze (row,column)
         */
{
    private int row;
    private int column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    public int getRowIndex()
    {
        return this.row;
    }
    public int getColumnIndex()
    {
        return this.column;
    }
    public String toString()
    {
        return "{" + this.row + "," + this.column + "}";
    }

    // Added methods

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static Position[] findLegalNeighbors (Position p, int max_row, int max_column) // gets a Position, and returns all the Possible moves (Positions) in the Maze
    {
        int NeighborsCounter = 0;
        Position [] poseArr = new Position[4];
        if(p.getRow() - 1 >= 0)
        {
            poseArr[NeighborsCounter] = new Position(p.getRow() - 1, p.getColumn()); // upper
            NeighborsCounter++;
        }
        if(p.getRow() + 1 < max_row)
        {
            poseArr[NeighborsCounter] = new Position(p.getRow() + 1, p.getColumn()); // lower
            NeighborsCounter++;
        }
        if(p.getColumn() + 1 < max_column)
        {
            poseArr[NeighborsCounter] = new Position(p.getRow(), p.getColumn() + 1); // right
            NeighborsCounter++;
        }
        if(p.getColumn() - 1 >= 0)
        {
            poseArr[NeighborsCounter] = new Position(p.getRow(), p.getColumn() - 1); // left
        }
        return poseArr;
    }

    public static Position findNextPose (Position p, int max_row, int max_column)
    {
        if(p.getRow() + 1 < max_row)
            return new Position(p.getRow() + 1, p.getColumn());
        else
            return new Position(p.getRow(), p.getColumn() + 1);
    }

}
