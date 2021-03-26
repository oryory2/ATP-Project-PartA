package algorithms.mazeGenerators;

public class Maze
{
    private Position StartPosition;
    private Position GoalPosition;
    private int [][] mazeArr;

    public Maze(int [][] mazeArr)
    {
        this.mazeArr = mazeArr;
        this.StartPosition = new Position(0,0);
        this.GoalPosition = new Position(mazeArr.length - 1, mazeArr[0].length - 1);
    }
    public Position getStartPosition()
    {
        return this.StartPosition;
    }
    public Position getGoalPosition()
    {
        return this.GoalPosition;
    }
    public void Print()
    {

        System.out.println("{");
        System.out.print("{ S ");
        for (int k = 1; k < mazeArr[0].length; k++) // First Row
        {
            if(k != mazeArr[0].length - 1)
                System.out.print(mazeArr[0][k] + " ");
            else
                System.out.println(mazeArr[0][k] + " }");
        }
        for(int i = 1; i < this.mazeArr.length - 1; i++) // Middle Rows
        {
            System.out.print("{ ");
            for (int j = 0; j < mazeArr[0].length; j++)
            {
                if(j != mazeArr[0].length - 1)
                    System.out.print(mazeArr[i][j] + " ");
                else
                    System.out.println(mazeArr[i][j] + " }");
            }
        }
        System.out.print("{ ");
        for (int t = 0; t < mazeArr[0].length; t++) // Last Row
        {
            if(t != mazeArr[0].length - 1)
                System.out.print(mazeArr[0][t] + " ");
            else
                System.out.println("E }");
        }
        System.out.println("}");
    }
}
