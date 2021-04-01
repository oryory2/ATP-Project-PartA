package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MyMaze3DGenerator extends AMaze3DGenerator
{
    public Maze3D generate(int depth, int row, int column) {
        int[][][] mazeArr = new int[depth][row][column];
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                for (int k = 0; k < mazeArr[0][0].length; k++) {
                    mazeArr[i][j][k] = 1;
                }
            }
        }
        mazeArr[0][0][0] = 0;

        Position3D thisPose = new Position3D(0, 0, 0);
        Position3D lastPose = thisPose;
        ArrayList<Position3D> PoseList = new ArrayList<Position3D>();
        int flag = 0;
        do {
            Position3D[] legalNeighbors = Position3D.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length, mazeArr[0][0].length);
            for (int i = 0; i < 6; i++) {
                if (legalNeighbors[i] == null)
                    break;
                if ((mazeArr[legalNeighbors[i].getDepth()][legalNeighbors[i].getRow()][legalNeighbors[i].getColumn()] != 1) || (legalNeighbors[i] == lastPose))
                    continue;
                PoseList.add(legalNeighbors[i]);
            }

            if (PoseList.isEmpty())
            {
                lastPose = thisPose;
                thisPose = Position3D.findNextPose(thisPose, mazeArr.length, mazeArr[0].length, mazeArr[0][0].length);
                continue;
            }
            int randomPick = (int) (Math.random() * (PoseList.size()));
            if (randomPick == PoseList.size())
                randomPick = randomPick - 1;

            Position3D p = PoseList.get(randomPick);
            if ((p.getDepth() == mazeArr.length - 1) && (p.getRow() == mazeArr[0].length - 1) && (p.getColumn() == mazeArr[0][0].length - 1)) {
                mazeArr[p.getDepth()][p.getRow()][p.getColumn()] = 0;
                flag = 1;
            } else {
                thisPose = p;
                mazeArr[p.getDepth()][p.getRow()][p.getColumn()] = 0;
                lastPose = thisPose;
                PoseList.clear();
            }
        }
        while (flag != 1);
        Maze3D newMaze = new Maze3D(mazeArr);
        return newMaze;
    }
}