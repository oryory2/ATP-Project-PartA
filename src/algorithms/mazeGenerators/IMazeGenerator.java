package algorithms.mazeGenerators;

public interface IMazeGenerator /** This Interface describe a MazeGenerator */

{
     /**
     * Returns a new general Maze
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return the new maze (Maze)
     */
    Maze generate (int row, int column);

     /**
     * Returns the time takes to the "generate" method to run
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return time in millis (long)
     */
    long measureAlgorithmTimeMillis(int row, int column);
}
