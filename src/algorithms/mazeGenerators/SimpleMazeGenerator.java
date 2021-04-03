package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator /** This Class describe MazeGenerator that build the Maze in a coincidence way */

{
     /**
     * Returns a new Simple Maze
     * all of the Arraycells of the maze will be randomize (0 or 1),
     * apart from one path from the startPose to goalPose
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return the new maze (Maze)
     */
    public Maze generate(int row, int column)
    {
        if((row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }

        int [][] mazeArr = new int[row][column];
        for(int i = 1; i < mazeArr.length; i++)
        // Randomize all the cells Value, and staying one sure path from the startPose to goalPose
        {
            for (int j = 0; j < mazeArr[0].length - 1; j++)
                mazeArr[i][j] = (int)(Math.random() * 2);
        }
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

}
