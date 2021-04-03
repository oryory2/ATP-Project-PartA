package algorithms.mazeGenerators;

public interface IMazeGenerator /** This Interface describe a MazeGenerator */

{
    Maze generate (int row, int column);
    long measureAlgorithmTimeMillis(int row, int column);
}
