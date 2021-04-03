package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D
{

    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        if((depth <= 1) || (row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Depth/Row/Column values must be at least 2");
        }
        long S_time = System.currentTimeMillis();
        this.generate(depth, row, column);
        long E_time = System.currentTimeMillis();
        return E_time - S_time;
    }

}
