package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D
{
    private int [][][] mazeArr;
    private int max_rows;
    private int max_columns;
    private int max_depth;
    private Position3D StartPosition;
    private Position3D GoalPosition;


    public Maze3D(int [][][] mazeArr)
    {
        this.mazeArr = mazeArr;

        this.max_depth = mazeArr.length;
        this.max_rows = mazeArr[0].length;
        this.max_columns = mazeArr[0][0].length;
        this.StartPosition = new Position3D(0,0,0);
        this.GoalPosition = new Position3D(mazeArr.length - 1,mazeArr[0].length - 1,mazeArr[0][0].length - 1);
    }

    public int[][][] getMap()
    {
        return mazeArr;
    }

    public int getMax_rows()
    {
        return max_rows;
    }

    public int getMax_columns()
    {
        return max_columns;
    }

    public int getMax_depth()
    {
        return max_depth;
    }

    public Position3D getStartPosition()
    {
        return StartPosition;
    }
    public Position3D getGoalPosition()
    {
        return GoalPosition;
    }

}
