package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator
{
    public Maze generate(int row, int column)
    {
        int [][] mazeArr = new int[row][column];
        for(int i = 1; i < mazeArr.length; i++)
        {
            for (int j = 0; j < mazeArr[0].length - 1; j++)
                mazeArr[i][j] = (int)(Math.random() * 2);
        }
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

}
