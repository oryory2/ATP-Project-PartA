package algorithms.maze3D;

public interface IMazeGenerator3D /** This Interface describe a 3DMazeGenerator */
{
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
