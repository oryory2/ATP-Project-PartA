package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator /** This Abstract Class describe a MazeGenerator that knows how to build the Maze */

{

    public long measureAlgorithmTimeMillis(int row, int column)
    {
        if((row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }
        long S_time = System.currentTimeMillis(); // Get the Current time (before running "generate")
        this.generate(row, column);
        long E_time = System.currentTimeMillis(); // Get the Current time (after running "generate")
        return E_time - S_time;
    }
}
