package algorithms.mazeGenerators;

public class Maze /** This Class describe a Maze from any Shape */

{
    private Position StartPosition;
    private Position GoalPosition;
    private int [][] mazeArr;
    private int max_rows;
    private int max_columns;

     /**
     * constructor
     * setting the StartPosition of the Maze to be {0, 0}
     * setting the GoalPosition of the Maze to be {mazeArr.length - 1, mazeArr[0].length - 1}
     * @param mazeArr the 2DArray that presenting the Maze
     */
    public Maze(int [][] mazeArr)
    {
        if(mazeArr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
        if((mazeArr.length <= 1) || (mazeArr[0].length <= 1))
        {
            throw new RuntimeException("The Array that supplied is not legal! it must have at least 2 Rows and 2 Columns");
        }
        this.mazeArr = mazeArr;
        // we chose the StartPosition to be (0,0)
        this.StartPosition = new Position(0,0);
        // we chose the GoalPosition to be (mazeArr.length - 1, mazeArr[0].length - 1)
        this.GoalPosition = new Position(mazeArr.length - 1, mazeArr[0].length - 1);
        this.max_rows = mazeArr.length;
        this.max_columns = mazeArr[0].length;
    }

     /**
     * @return the StartPosition of the maze (Position)
     */
    public Position getStartPosition()
    {
        return this.StartPosition;
    }

     /**
     * @return the GoalPosition of the maze (Position)
     */
    public Position getGoalPosition()
    {
        return this.GoalPosition;
    }

     /**
     * Printing method for the Maze
     * Printing the maze according to the 2DArray representing it
     */
    public void print()
    {

        System.out.println("{");
        System.out.print("{ S ");
        for (int k = 1; k < mazeArr[0].length; k++) // First Row
        {
            if(k != mazeArr[0].length - 1)
                System.out.print(mazeArr[0][k] + " ");
            else
                System.out.println(mazeArr[0][k] + " }");
        }
        for(int i = 1; i < this.mazeArr.length - 1; i++) // Middle Rows
        {
            System.out.print("{ ");
            for (int j = 0; j < mazeArr[0].length; j++)
            {
                if(j != mazeArr[0].length - 1)
                    System.out.print(mazeArr[i][j] + " ");
                else
                    System.out.println(mazeArr[i][j] + " }");
            }
        }
        System.out.print("{ ");
        for (int t = 0; t < mazeArr[0].length; t++) // Last Row
        {
            if(t != mazeArr[0].length - 1)
                System.out.print(mazeArr[mazeArr.length - 1][t] + " ");
            else
                System.out.println("E }");
        }
        System.out.println("}");
    }

     /**
     * @return the 2DArray representing the maze (int [][])
     */
    public int [][] getMazeArr()
    {
        return this.mazeArr;
    }

     /**
     * @return the number of rows in the maze (int)
     */
    public int getMax_rows() {
        return max_rows;
    }

     /**
     * @return the number of columns in the maze (int)
     */
    public int getMax_columns() {
        return max_columns;
    }
}
