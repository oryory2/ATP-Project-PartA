package algorithms.maze3D;

public interface IMaze3DGenerator /** This Interface describe a 3DMazeGenerator */
{
     /**
     * Returns a new general 3DMaze
     * @param depth The depth of the maze
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return the new maze (Maze3D)
     */
    Maze3D generate(int depth, int row, int column);

     /**
     * Returns the time takes to the "generate" method to run
     * @param depth The depth of the maze
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return time in millis (long)
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
