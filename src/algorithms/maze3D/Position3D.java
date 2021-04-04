package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Position3D /** This Class describe a specific 3DPosition in a 3DMaze (depth,row,column) */
{
    // We chose to describe illegal Position by {-1,-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

    private int depth;
    private int row;
    private int column;

     /**
     * constructor
     * initializing a new Position3D, described by {depth,row,column}
     * @param depth The depth index of the Position3D
     * @param row The row index of the Position3D
     * @param column The column index of the Position3D
     */
    public Position3D(int depth, int row, int column)
    {
        if((depth < 0) || (row < 0) || (column < 0))
        {
            if(!((depth == -1) && (row == -1) && (column == -1)))
                throw new RuntimeException("One or more of the supplied sizes are not legal! Position3D can't have negative indexes");
        }
        this.depth = depth;
        this.row = row;
        this.column = column;
    }

     /**
     * @return The display of the Position3D - {depth,row,column} (String)
     */
    public String toString()
    {
        return "{" + this.depth + "," + this.row + "," + this.column + "}";
    }

     /**
     * @return The Depth index of the Position3D (int)
     */
    public int getDepth()
    {
        return this.depth;
    }

     /**
     * @return The row index of the Position3D (int)
     */
    public int getRow()
    {
        return this.row;
    }

     /**
     * @return The Column index of the Position3D (int)
     */
    public int getColumn()
    {
        return this.column;
    }

     /**
     * gets a Position3D, and returns all the Possible legal moves (Position3D) from
     * the specific Position3D by the depth/row/columns borders
     * @param p The Specific Position
     * @param max_depth The depth of the maze
     * @param max_row The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return List of possible Positions (Position3D[])
     */
    public static Position3D[] findLegalNeighbors (Position3D p, int max_depth, int max_row, int max_column)
    {
        if(p == null)
        {
            throw new RuntimeException("The Position3D that supplied is not legal (null)");
        }
        if((p.getDepth() < 0) || (p.getRow() < 0) || (p.getColumn() < 0))
        {
            if(!((p.getDepth() == -1) && (p.getRow() == -1) && (p.getColumn() == -1)))
                throw new RuntimeException("One or more of the Position3D indexes are not legal! Position3D can't have negative indexes");
        }

        int NeighborsCounter = 0;
        Position3D [] poseArr = new Position3D[6];

        // for each possible move (up, down, left, right, inside, outside) we check if it's a valid move
        if(p.row - 1 >= 0) // up
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth, p.row - 1, p.column);
            NeighborsCounter++;
        }
        if(p.column + 1 < max_column) // right
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth, p.row, p.column + 1);
            NeighborsCounter++;
        }
        if(p.row + 1 < max_row) // down
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth, p.row + 1, p.column);
            NeighborsCounter++;
        }
        if(p.column - 1 >= 0) // left
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth, p.row, p.column - 1);
            NeighborsCounter++;
        }
        if(p.depth + 1 < max_depth) // inside
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth + 1, p.row, p.column);
            NeighborsCounter++;
        }
        if(p.depth - 1 >= 0) // outside
        {
            poseArr[NeighborsCounter] = new Position3D(p.depth - 1, p.row, p.column);
        }
        return poseArr;
    }

     /**
     * gets a stuck Position3D (we visited in all the possible NeighborsPositions),
     * and returns an optional Position3D (inside/down/right) - one of them will be valid because we are not in the GoalPosition
     * @param thisPose The Specific Position3D
     * @param max_depth The depth of the maze
     * @param max_row The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return The next optional Position (Position3D)
     */
    public static Position3D findNextPose(Position3D thisPose, int max_depth, int max_row, int max_column)
    {
        if(thisPose == null)
        {
            throw new RuntimeException("The Position3D that supplied is not legal (null)");
        }
        if((thisPose.getDepth() < 0) || (thisPose.getRow() < 0) || (thisPose.getColumn() < 0))
        {
            if(!((thisPose.getDepth() == -1) && (thisPose.getRow() == -1) && (thisPose.getColumn() == -1)))
                throw new RuntimeException("One or more of the Position3D indexes are not legal! Position3D can't have negative indexes");
        }

        // we choose our next Position by moving inside/up/right (one of them must be valid, because we are not in the GoalPosition)
        if(thisPose.depth + 1 < max_depth) // inside
            return new Position3D(thisPose.depth + 1, thisPose.getRow(), thisPose.getColumn());
        else if(thisPose.row + 1 < max_row) // up
            return new Position3D(thisPose.depth, thisPose.getRow() + 1, thisPose.getColumn());
        else                                // right
            return new Position3D(thisPose.depth, thisPose.getRow(), thisPose.getColumn() + 1);
    }
}
