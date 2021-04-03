package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Position3D
{
    private int depth;
    private int row;
    private int column;

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

    public String toString()
    {
        return "{" + this.depth + "," + this.row + "," + this.column + "}";
    }

    public int getRow()
    {
        return this.row;
    }
    public int getColumn()
    {
        return this.column;
    }
    public int getDepth()
    {
        return this.depth;
    }

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
        if(thisPose.depth + 1 < max_depth)
            return new Position3D(thisPose.depth + 1, thisPose.getRow(), thisPose.getColumn());
        else if(thisPose.row + 1 < max_row)
            return new Position3D(thisPose.depth, thisPose.getRow() + 1, thisPose.getColumn());
        else
            return new Position3D(thisPose.depth, thisPose.getRow(), thisPose.getColumn() + 1);
    }
}
