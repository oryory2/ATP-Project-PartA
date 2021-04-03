package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator
        /**
         * This Class describe MazeGenerator that build an empty maze
         */
{
    public Maze generate(int row, int column)
    {
        if((row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }
        int [][] mazeArr = new int[row][column];
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }
}
