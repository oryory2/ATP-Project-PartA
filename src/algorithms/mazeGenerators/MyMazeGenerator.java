package algorithms.mazeGenerators;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator
{
    public Maze generate(int row, int column) {
        int[][] mazeArr = new int[row][column];
        for (int i = 0; i <= mazeArr.length; i++)
        {
            for (int j = 0; j <= mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }
        mazeArr[0][0] = 0;
        Position thisPose = new Position(0,0);
        ArrayList<Position> PoseList = new ArrayList<Position>();
        do
            {
                Position [] legalNeighbors = Position.findLegalNeighbors(thisPose, thisPose.getRow(), thisPose.getColumn());
                for(int i = 0; i < 4; i++)
                {
                    if(legalNeighbors[i] == null)
                        break;
                    if(mazeArr[legalNeighbors[i].getRow()][legalNeighbors[i].getColumn()] == 1)
                        PoseList.add(legalNeighbors[i]);
                }
                int flag = 0;
                int randomPick = (int) (Math.random() * PoseList.size());
                Position p = PoseList.get(randomPick);
                if((p.getRow() == mazeArr.length - 1) && (p.getColumn() == mazeArr[0].length - 1))
                {
                    mazeArr[mazeArr.length][mazeArr[0].length] = 0;
                    Maze newMaze = new Maze(mazeArr);
                    return newMaze;
                }
                else
                {
                    thisPose = p;
                    PoseList.remove(p);
                    mazeArr[thisPose.getRow()][thisPose.getColumn()] = 0;
                }
        }
        while(!(PoseList.isEmpty()));
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

}
