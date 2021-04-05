package algorithms.mazeGenerators;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator /** This Class describe MazeGenerator that build the Maze by the DFS method */

{
/*    public Maze generate(int row, int column) {
        if ((row <= 1) || (column <= 1)) {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }

        int[][] mazeArr = new int[row][column];
        int helper = 0;
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }
        mazeArr[0][0] = 0;
        Position thisPose = new Position(0, 0);
        Position lastPose = thisPose;
        ArrayList<Position> PoseList = new ArrayList<Position>();
        int flag = 0;
        do {
            Position[] legalNeighbors = Position.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length);
            for (int i = 0; i < 4; i++) {
                if (legalNeighbors[i] == null)
                    break;
                if ((mazeArr[legalNeighbors[i].getRow()][legalNeighbors[i].getColumn()] != 1) || (legalNeighbors[i] == lastPose))
                    continue;
                PoseList.add(legalNeighbors[i]);
            }

            if (PoseList.isEmpty()) {
                lastPose = thisPose;
                thisPose = Position.findNextPose(thisPose, mazeArr.length, mazeArr[0].length, helper);
                helper++;
                continue;
            }
            int randomPick = (int) (Math.random() * (PoseList.size()));
            if (randomPick == PoseList.size())
                randomPick = randomPick - 1;

            Position p = PoseList.get(randomPick);
            if ((p.getRow() == mazeArr.length - 1) && (p.getColumn() == mazeArr[0].length - 1)) {
                mazeArr[p.getRow()][p.getColumn()] = 0;
                flag = 1;
            } else {
                thisPose = p;
                mazeArr[thisPose.getRow()][thisPose.getColumn()] = 0;
                lastPose = thisPose;
                PoseList.clear();
            }
        }
        while (flag != 1);
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }*/

    public Maze generate(int row, int column) {
        if ((row <= 1) || (column <= 1)) {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }

        int[][] mazeArr = new int[row][column];
        int helper = 0;
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }
        //start position
        mazeArr[0][0] = 0;
        //array of frontiers = a cell with distance 2 in state Blocked
        ArrayList<Position> Front = new ArrayList<Position>();
        Position thisPose = new Position(0, 0);
        Position[] blocked_nei = Position.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length);
        //add all frontiers of the first state
        for (int i = 0; i < 4; i++) {
            if (blocked_nei[i] == null)
                break;
            if ((mazeArr[blocked_nei[i].getRow()][blocked_nei[i].getColumn()] == 1))
                Front.add(blocked_nei[i]);
        }

        while (!(Front.isEmpty())) {
            int randomPick = (int) (Math.random() * (Front.size()));
            //random frontier from the list
            Position randFront = Front.get(randomPick);
            //frontiers neighbours list
            ArrayList<Position> rand_neighbor_open = new ArrayList<Position>();
            Position[] all_nei_2 = Position.findLegalNeighbors(randFront, mazeArr.length, mazeArr[0].length);
            //add all neighbors of the frontier
            for (int i = 0; i < 4; i++) {
                if (all_nei_2[i] == null)
                    break;
                if ((mazeArr[all_nei_2[i].getRow()][all_nei_2[i].getColumn()] == 0))
                    rand_neighbor_open.add(all_nei_2[i]);
            }

            //
            int randomNei = (int) (Math.random() * (rand_neighbor_open.size()));
            //choose a random frontier neighbour and change the cell between them
            Position singleRandNei = rand_neighbor_open.get(randomNei);
            if (randFront.getRow() == singleRandNei.getRow()) {
                mazeArr[randFront.getRow()][randFront.getColumn()] = 0;
                mazeArr[randFront.getRow()][(randFront.getColumn() + singleRandNei.getColumn()) / 2] = 0;
            }
            if (randFront.getColumn() == singleRandNei.getColumn()) {
                mazeArr[randFront.getRow()][randFront.getColumn()] = 0;
                mazeArr[(randFront.getRow() + singleRandNei.getRow()) / 2][randFront.getColumn()] = 0;
            }
            for (int i = 0; i < 4; i++) {
                if (all_nei_2[i] == null)
                    break;
                if ((mazeArr[all_nei_2[i].getRow()][all_nei_2[i].getColumn()] == 1))
                    Front.add(all_nei_2[i]);
            }

            Front.remove(randFront);
        }
        mazeArr[mazeArr.length-1][mazeArr[0].length-1] = 0;
        // even even
        if(mazeArr.length % 2 == 0 && mazeArr[0].length % 2 == 0){

            for(int i = 0;i < mazeArr.length - 1; i++){
                mazeArr[i][mazeArr[0].length-1] = (int) (Math.random() * 2);
            }

            for(int j = 0;j < mazeArr[0].length - 1; j++){
                mazeArr[mazeArr.length - 1][j] = (int) (Math.random() * 2);
            }
            mazeArr[mazeArr.length-2][mazeArr[0].length-1] = 0;
        }

        //even odd
        if(mazeArr.length % 2 == 0 && mazeArr[0].length % 2 != 0){
            for(int j = 0;j < mazeArr[0].length - 1; j++){
                mazeArr[mazeArr.length - 1][j] = (int) (Math.random() * 2);
            }
        }

        //odd even
        if(mazeArr.length % 2 != 0 && mazeArr[0].length % 2 == 0){
            for(int i = 0;i < mazeArr.length - 1; i++){
                mazeArr[i][mazeArr[0].length-1] = (int) (Math.random() * 2);
            }
        }



            Maze newMaze = new Maze(mazeArr);
            return newMaze;
    }
}