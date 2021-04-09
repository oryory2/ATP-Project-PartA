package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator /** This Abstract Class describe a 3DMazeGenerator that knows how to build the 3DMaze */
{

    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        if((depth <= 1) || (row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Depth/Row/Column values must be at least 2");
        }
        long S_time = System.currentTimeMillis(); // Get the Current time (before running "generate")
        this.generate(depth, row, column);
        long E_time = System.currentTimeMillis(); // Get the Current time (after running "generate")
        return E_time - S_time;
    }

}
