package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator
{
    public Maze generate(int row, int column)
    {
        int [][] mazeArr = new int[row][column];
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }
}
