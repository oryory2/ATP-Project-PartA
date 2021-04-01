package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D
{
    private int depth;
    private int row;
    private int column;

    public Position3D(int depth, int row, int column)
    {
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
        if(thisPose.depth + 1 < max_depth)
            return new Position3D(thisPose.depth + 1, thisPose.getRow(), thisPose.getColumn());
        else if(thisPose.row + 1 < max_row)
            return new Position3D(thisPose.depth, thisPose.getRow() + 1, thisPose.getColumn());
        else
            return new Position3D(thisPose.depth, thisPose.getRow(), thisPose.getColumn() + 1);
    }
}
