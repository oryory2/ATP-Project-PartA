package algorithms.mazeGenerators;

import java.util.List;

public class Position /** This Class describe a specific Position in a Maze (row,column) */
{
    private int row;
    private int column;

    // We chose to describe illegal Position by {-1,-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

     /**
     * constructor
     * initializing a new Position, described by {row,column}
     * @param row The row index of the Position
     * @param column The column index of the Position
     */
    public Position(int row, int column)
    {
        if((row < 0) || (column < 0))
        {
            if(!((row == -1) && (column == -1)))
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
    public String toString()
    {
        return "{" + this.row + "," + this.column + "}";
    }

     /**
     * gets a Position, and returns all the Possible legal moves (Positions) from
     * the specific Position by the row and columns borders
     * @param p The Specific Position
     * @param max_row The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return List of possible Positions (Position[])
     */
    public static Position[] findLegalNeighbors (Position p, int max_row, int max_column) // gets a Position, and returns all the Possible moves (Positions) in the Maze
    {
        if(p == null)
        {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if((p.getRow() < 0) || (p.getColumn() < 0))
        {
            if(!((p.getRow() == -1) && (p.getColumn() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }

        // for each possible move (up, down, left, right) we check if it's a valid move
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

     /**
     * gets a stuck Position (we visited in all the possible NeighborsPositions),
     * and returns an optional Position (down/right) - one of them will be valid because we are not in the GoalPosition
     * @param p The Specific Position
     * @param max_row The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return The next optional Position (Position)
     */
    public static Position findNextPose (Position p, int max_row, int max_column, int helper)
    {
        if(p == null)
        {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if((p.getRow() < 0) || (p.getColumn() < 0))
        {
            if(!((p.getRow() == -1) && (p.getColumn() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }

        // we choose our next Position by moving up/right (one of them must be valid, because we are not in the GoalPosition)
        if(helper % 2 == 0)
        {
            if (p.getRow() + 1 < max_row)
                return new Position(p.getRow() + 1, p.getColumn());
            else
                return new Position(p.getRow(), p.getColumn() + 1);
        }
        else
        {
            if (p.getColumn() + 1 < max_column)
                return new Position(p.getRow(), p.getColumn() + 1);
            else
                return new Position(p.getRow() + 1, p.getColumn());
        }
    }
}
