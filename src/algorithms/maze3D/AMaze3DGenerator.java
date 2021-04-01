package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D
{

    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        long S_time = System.currentTimeMillis();
        this.generate(depth, row, column);
        long E_time = System.currentTimeMillis();
        return E_time - S_time;
    }

}
