package algorithms.mazeGenerators;

public class Position
{
    private int row;
    private int column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    public int getRowIndex()
    {
        return this.row;
    }
    public int getColumnIndex()
    {
        return this.column;
    }
    public String toString()
    {
        return "{" + this.row + "," + this.column + "}";
    }
}
