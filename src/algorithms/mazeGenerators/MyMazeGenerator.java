package algorithms.mazeGenerators;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator /** This Class describe MazeGenerator that build the Maze by the DFS method */

{
    public Maze generate(int row, int column) {
        if ((row <= 1) || (column <= 1)) {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }
        int[][] mazeArr = new int[row][column];
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }

        mazeArr[0][0] = 0; //start position
        ArrayList<Position> FrontVal1 = new ArrayList<Position>(); // array of Positions 2 steps away from thisPose, with value 1
        Position thisPose = new Position(0, 0);
        Position[] startPoseNeighbors = Position.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length);
        for (int i = 0; i < 4; i++) {
            if (startPoseNeighbors[i] == null)
                break;
            if ((mazeArr[startPoseNeighbors[i].getRow()][startPoseNeighbors[i].getColumn()] == 1))
                FrontVal1.add(startPoseNeighbors[i]);
        }

        while (!(FrontVal1.isEmpty())) {
            int randomPick = (int) (Math.random() * (FrontVal1.size()));
            Position randFront = FrontVal1.get(randomPick); // choose a random Wall
            ArrayList<Position> BackVal0 = new ArrayList<Position>(); // array of Positions 2 steps away from randFront, with value 0
            Position[] randFrontNeighbors = Position.findLegalNeighbors(randFront, mazeArr.length, mazeArr[0].length);
            for (int i = 0; i < 4; i++) {
                if (randFrontNeighbors[i] == null)
                    break;
                if ((mazeArr[randFrontNeighbors[i].getRow()][randFrontNeighbors[i].getColumn()] == 0))
                    BackVal0.add(randFrontNeighbors[i]);
                if ((mazeArr[randFrontNeighbors[i].getRow()][randFrontNeighbors[i].getColumn()] == 1))
                    FrontVal1.add(randFrontNeighbors[i]);
            }
            int randomNei = (int) (Math.random() * (BackVal0.size()));
            Position randBack = BackVal0.get(randomNei); // choose a random Position from randNeighbor and change the value of the cell between them and the randFront to 0
            updatePositionsVal(randFront, randBack, mazeArr); // updating the values of the wanted Positions

            FrontVal1.remove(randFront);
        }
        FinishMaze(mazeArr);
        mazeArr[mazeArr.length-1][mazeArr[0].length-1] = 0;
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }

    private void updatePositionsVal (Position front, Position back, int [][] mazeArr)
    {
        if (front.getRow() == back.getRow()) {
            mazeArr[front.getRow()][front.getColumn()] = 0;
            mazeArr[front.getRow()][(front.getColumn() + back.getColumn()) / 2] = 0;
        }
        if (front.getColumn() == back.getColumn()) {
            mazeArr[front.getRow()][front.getColumn()] = 0;
            mazeArr[(front.getRow() + back.getRow()) / 2][front.getColumn()] = 0;
        }
    }

    private void FinishMaze(int [][] mazeArr)
    {
        if(mazeArr.length % 2 != 0 && mazeArr[0].length % 2 != 0)
            return;
        if(mazeArr.length % 2 == 0 && mazeArr[0].length % 2 == 0) // even even
        {
            for(int i = 0;i < mazeArr.length - 1; i++){
                mazeArr[i][mazeArr[0].length-1] = (int) (Math.random() * 2);
            }
            for(int j = 0;j < mazeArr[0].length - 1; j++){
                mazeArr[mazeArr.length - 1][j] = (int) (Math.random() * 2);
            }
            mazeArr[mazeArr.length-2][mazeArr[0].length-1] = 0;
        }
        if(mazeArr.length % 2 == 0 && mazeArr[0].length % 2 != 0) //even odd
        {
            for(int j = 0;j < mazeArr[0].length - 1; j++){
                mazeArr[mazeArr.length - 1][j] = (int) (Math.random() * 2);
            }
        }
        if(mazeArr.length % 2 != 0 && mazeArr[0].length % 2 == 0) //odd even
        {
            for(int i = 0;i < mazeArr.length - 1; i++){
                mazeArr[i][mazeArr[0].length-1] = (int) (Math.random() * 2);
            }
        }
    }
}