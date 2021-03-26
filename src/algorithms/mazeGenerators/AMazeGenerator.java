package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator
{
    public long measureAlgorithmTimeMillis(int row, int column)
    {
        long S_time = System.currentTimeMillis();
        this.generate(row, column);
        long E_time = System.currentTimeMillis();
        return E_time - S_time;
    }
}
