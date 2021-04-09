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
    public int getDepthIndex()
    {
        return this.depth;
    }

     /**
     * @return The row index of the Position3D (int)
     */
    public int getRowIndex()
    {
        return this.row;
    }

     /**
     * @return The Column index of the Position3D (int)
     */
    public int getColumnIndex()
    {
        return this.column;
    }
}
