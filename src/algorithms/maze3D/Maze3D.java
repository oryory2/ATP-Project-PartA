package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D /** This Class describe a 3DMaze from any Shape */
{
    private int[][][] mazeArr;
    private int max_rows;
    private int max_columns;
    private int max_depth;
    private Position3D startPosition;
    private Position3D goalPosition;


     /**
     * constructor
     * setting the StartPosition of the Maze to be {0,0,0}
     * setting the GoalPosition of the Maze to be {mazeArr.length - 1, mazeArr[0].length - 1, mazeArr[0][0].length - 1}
     * @param mazeArr the 3DArray that presenting the Maze
     */
    public Maze3D(int[][][] mazeArr)
    {
        if(mazeArr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
        if((mazeArr.length <= 1) ||(mazeArr[0].length <= 1) || (mazeArr[0][0].length <= 1))
        {
            throw new RuntimeException("The Array that supplied is not legal! Depth/Row/Column values must be at least 2");
        }
        this.mazeArr = mazeArr;
        this.max_depth = mazeArr.length;
        this.max_rows = mazeArr[0].length;
        this.max_columns = mazeArr[0][0].length;
        // we chose the StartPosition to be (0,0,0)
        this.startPosition = new Position3D(0, 0, 0);
        // we chose the GoalPosition to be (mazeArr.length - 1,mazeArr[0].length - 1,mazeArr[0][0].length - 1)
        this.goalPosition = new Position3D(mazeArr.length - 1, mazeArr[0].length - 1, mazeArr[0][0].length - 1);
    }

     /**
     * @return the 3DArray representing the maze (int [][][])
     */
    public int[][][] getMap() { return mazeArr; }

    /**
     * @return the depth of the maze (int)
     */
    public int getMax_depth() {
        return max_depth;
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

     /**
     * @return the StartPosition of the maze (Position3D)
     */
    public Position3D getStartPosition() {
        return startPosition;
    }

     /**
     * @return the GoalPosition of the maze (Position3D)
     */
    public Position3D getGoalPosition() {
        return goalPosition;
    }

     /**
     * Printing method for the Maze3D
     * Printing the maze according to the 3DArray representing it
     */
     public void print(){
         System.out.println("{");
         for(int depth = 0; depth < mazeArr.length; depth++){
             for(int row = 0; row < mazeArr[0].length; row++) {
                 System.out.print("{ ");
                 for (int col = 0; col < mazeArr[0][0].length; col++) {
                     if (depth == startPosition.getDepth() && row == startPosition.getRow() && col == startPosition.getColumn()) // if the position is the start - mark with S
                         System.out.print("S ");
                     else {
                         if (depth == goalPosition.getDepth() && row == goalPosition.getRow() && col == goalPosition.getColumn()) // if the position is the goal - mark with E
                             System.out.print("E ");
                         else
                             System.out.print(mazeArr[depth][row][col] + " ");
                     }
                 }
                 System.out.println("}");
             }
             if(depth < mazeArr.length - 1) {
                 System.out.print("---");
                 for (int i = 0; i < mazeArr[0][0].length; i++)
                     System.out.print("--");
                 System.out.println();
             }
         }
         System.out.println("}");
     }
}