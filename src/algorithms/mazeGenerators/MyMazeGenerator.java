package algorithms.mazeGenerators;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator /** This Class describe MazeGenerator that build the Maze by the DFS method */

{
    public Maze generate(int row, int column)
    {
        if((row <= 1) || (column <= 1))
        {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }

        int[][] mazeArr = new int[row][column];
        int helper = 0;
        for (int i = 0; i < mazeArr.length; i++)
        {
            for (int j = 0; j < mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }
        mazeArr[0][0] = 0;
        Position thisPose = new Position(0,0);
        Position lastPose = thisPose;
        ArrayList<Position> PoseList = new ArrayList<Position>();
        int flag = 0;
        do
        {
            Position [] legalNeighbors = Position.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length);
            for(int i = 0; i < 4; i++)
            {
                if(legalNeighbors[i] == null)
                    break;
                if((mazeArr[legalNeighbors[i].getRow()][legalNeighbors[i].getColumn()] != 1) || (legalNeighbors[i] == lastPose))
                    continue;
                PoseList.add(legalNeighbors[i]);
            }

            if(PoseList.isEmpty())
            {
                lastPose = thisPose;
                thisPose = Position.findNextPose(thisPose, mazeArr.length, mazeArr[0].length, helper);
                helper++;
                continue;
            }
            int randomPick = (int) (Math.random() * (PoseList.size()));
            if(randomPick == PoseList.size())
                randomPick = randomPick - 1;

            Position p = PoseList.get(randomPick);
            if((p.getRow() == mazeArr.length - 1) && (p.getColumn() == mazeArr[0].length - 1))
            {
                mazeArr[p.getRow()][p.getColumn()] = 0;
                flag = 1;
            }
            else
            {
                thisPose = p;
                mazeArr[thisPose.getRow()][thisPose.getColumn()] = 0;
                lastPose = thisPose;
                PoseList.clear();
            }
        }
        while(flag != 1);

        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

}