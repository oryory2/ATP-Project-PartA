package algorithms.mazeGenerators;

import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator
{
    public Maze generate(int row, int column) {
        int[][] mazeArr = new int[row][column];
        for (int i = 0; i <= mazeArr.length; i++) {
            for (int j = 0; j <= mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }
        mazeArr[0][0] = 0;
        Position thisPose = new Position(0,0);
        Set<String> wallSet = new HashSet<String>();

        do
            {



        }
        while(!(wallSet.isEmpty()));










        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

}
