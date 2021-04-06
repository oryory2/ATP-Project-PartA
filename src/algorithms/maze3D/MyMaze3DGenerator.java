package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MyMaze3DGenerator extends AMaze3DGenerator /** This Class describe 3DMazeGenerator that build the Maze by the DFS method */
{
//    public Maze3D generate(int depth, int row, int column)
//    {
//
//        if((depth <= 1) || (row <= 1) || (column <= 1))
//        {
//            throw new RuntimeException("One or more of the supplied sizes are not legal! Depth/Row/Column values must be at least 2");
//        }
//        int[][][] mazeArr = new int[depth][row][column];
//        for (int i = 0; i < mazeArr.length; i++)
//        {
//            for (int j = 0; j < mazeArr[0].length; j++)
//            {
//                for (int k = 0; k < mazeArr[0][0].length; k++)
//                {
//                    mazeArr[i][j][k] = 1;
//                }
//            }
//        }
//        mazeArr[0][0][0] = 0;
//
//        Position3D thisPose = new Position3D(0, 0, 0);
//        Position3D lastPose = thisPose;
//        ArrayList<Position3D> PoseList = new ArrayList<Position3D>();
//        int flag = 0;
//        do {
//            Position3D[] legalNeighbors = Position3D.findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length, mazeArr[0][0].length);
//            for (int i = 0; i < 6; i++) {
//                if (legalNeighbors[i] == null)
//                    break;
//                if ((mazeArr[legalNeighbors[i].getDepth()][legalNeighbors[i].getRow()][legalNeighbors[i].getColumn()] != 1) || (legalNeighbors[i] == lastPose))
//                    continue;
//                PoseList.add(legalNeighbors[i]);
//            }
//
//            if (PoseList.isEmpty())
//            {
//                lastPose = thisPose;
//                thisPose = Position3D.findNextPose(thisPose, mazeArr.length, mazeArr[0].length, mazeArr[0][0].length);
//                continue;
//            }
//            int randomPick = (int) (Math.random() * (PoseList.size()));
//            if (randomPick == PoseList.size())
//                randomPick = randomPick - 1;
//
//            Position3D p = PoseList.get(randomPick);
//            if ((p.getDepth() == mazeArr.length - 1) && (p.getRow() == mazeArr[0].length - 1) && (p.getColumn() == mazeArr[0][0].length - 1)) {
//                mazeArr[p.getDepth()][p.getRow()][p.getColumn()] = 0;
//                flag = 1;
//            } else {
//                thisPose = p;
//                mazeArr[p.getDepth()][p.getRow()][p.getColumn()] = 0;
//                lastPose = thisPose;
//                PoseList.clear();
//            }
//        }
//        while (flag != 1);
//        Maze3D newMaze = new Maze3D(mazeArr);
//        return newMaze;
//    }

    public Maze3D generate(int depth, int row, int column) {
        if ((depth <= 1) || (row <= 1) || (column <= 1)) {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Depth/Row/Column values must be at least 2");
        }
        int[][][] mazeArr = new int[depth][row][column];
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                for (int k = 0; k < mazeArr[0][0].length; k++) {
                    mazeArr[i][j][k] = 1;
                }
            }
        }
        //start position
        mazeArr[0][0][0] = 0;

        //array of frontiers = a cell with distance 2 in state Blocked
        ArrayList<Position3D> Front = new ArrayList<Position3D>();
        Position3D thisPose = new Position3D(0, 0, 0);
        Position3D[] blocked_nei = Position3D.findLegalNeighbors(thisPose, depth, row, column);
        //add all frontiers of the first state
        for (int i = 0; i < 6; i++) {
            if (blocked_nei[i] == null)
                break;
            if ((mazeArr[blocked_nei[i].getRow()][blocked_nei[i].getRow()][blocked_nei[i].getColumn()] == 1)) {
                Front.add(blocked_nei[i]);
            }
        }

        while (!(Front.isEmpty())) {
            int randomPick = (int) (Math.random() * (Front.size()));
            //random frontier from the list
            Position3D randFront = Front.get(randomPick);
            //frontiers neighbours list
            ArrayList<Position3D> rand_neighbor_open = new ArrayList<Position3D>();
            Position3D[] all_nei_2 = Position3D.findLegalNeighbors(randFront, depth, row, column);
            //add all neighbors of the frontier
            for (int i = 0; i < 6; i++) {
                if (all_nei_2[i] == null)
                    break;
                if ((mazeArr[all_nei_2[i].getDepth()][all_nei_2[i].getRow()][all_nei_2[i].getColumn()] == 0))
                    rand_neighbor_open.add(all_nei_2[i]);
            }
            //
            int randomNei = (int) (Math.random() * (rand_neighbor_open.size()));
            //choose a random frontier neighbour and change the cell between them
            Position3D singleRandNei = rand_neighbor_open.get(randomNei);
            //The rows and columns are equal - we will change the depth
            if (randFront.getRow() == singleRandNei.getRow() && randFront.getColumn() == singleRandNei.getColumn()) {
                mazeArr[randFront.getDepth()][randFront.getRow()][randFront.getColumn()] = 0;
                mazeArr[(randFront.getDepth() + singleRandNei.getDepth()) / 2][randFront.getRow()][randFront.getColumn()] = 0;
            }
            //The rows and depth are equal - we will change the columns
            if (randFront.getRow() == singleRandNei.getRow() && randFront.getDepth() == singleRandNei.getDepth()) {
                mazeArr[randFront.getDepth()][randFront.getRow()][randFront.getColumn()] = 0;
                mazeArr[randFront.getDepth()][randFront.getRow()][(randFront.getColumn() + singleRandNei.getColumn()) / 2] = 0;
            }
            //The depth and columns are equal - we will change the rows
            if (randFront.getDepth() == singleRandNei.getDepth() && randFront.getColumn() == singleRandNei.getColumn()) {
                mazeArr[randFront.getDepth()][randFront.getRow()][randFront.getColumn()] = 0;
                mazeArr[randFront.getDepth()][(randFront.getRow() + singleRandNei.getRow()) / 2][randFront.getColumn()] = 0;
            }


            for (int i = 0; i < 6; i++) {
                if (all_nei_2[i] == null)
                    break;
                if ((mazeArr[all_nei_2[i].getDepth()][all_nei_2[i].getRow()][all_nei_2[i].getColumn()] == 1))
                    Front.add(all_nei_2[i]);
            }

            Front.remove(randFront);
        }

        //Odd depth, odd rows, odd columns -> normal situation dont do anything
        ///////////////////////////////////////////////////////////////////////

        //Odd depth, odd rows, even columns
        if(depth % 2 != 0 && row % 2 != 0 && column % 2 == 0){
            //fix last column
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < row - 1; j++){
                    mazeArr[i][j][column-1] = (int) (Math.random() * 2);
                }
            }
        }

        //Odd depth, even rows, odd columns
        if(depth % 2 != 0 && row % 2 == 0 && column % 2 != 0){
            //fix last row
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < column - 1; j++){
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
        }
        //Odd depth, even rows, even columns
        if(depth % 2 != 0 && row % 2 == 0 && column % 2 == 0){
            //fix last column
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < row - 1; j++){
                    mazeArr[i][j][column-1] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < column - 1; j++){
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 2);
            if(helper == 0){
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            if(helper == 1){
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
        }
        //Even depth, odd rows, odd columns
        if(depth % 2 == 0 && row % 2 != 0 && column % 2 != 0){
            //fix last depth
            for(int i = 0;i < row; i++){
                for(int j = 0;j < column; j++){
                    mazeArr[depth-1][i][j] = (int) (Math.random() * 2);
                }
            }
        }
        //Even depth, even rows, odd columns
        if(depth % 2 == 0 && row % 2 == 0 && column % 2 != 0){
            //fix last depth
            for(int i = 0;i < row; i++){
                for(int j = 0;j < column; j++){
                    mazeArr[depth-1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < column - 1; j++){
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }

            int helper = (int) (Math.random() * 2);
            if(helper == 0){
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            if(helper == 1){
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }

        }
        //Even depth, even rows, even columns
        if(depth % 2 == 0 && row % 2 == 0 && column % 2 == 0){
            //fix last depth
            for(int i = 0;i < row; i++){
                for(int j = 0;j < column; j++){
                    mazeArr[depth-1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < column - 1; j++){
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
            //fix last column
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < row - 1; j++){
                    mazeArr[i][j][column-1] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 6);
            //go down and then inside
            if(helper == 0){
                mazeArr[depth - 2][row - 1][column - 2] = 0;
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
            //go down and then right
            if(helper == 1){
                mazeArr[depth - 2][row - 1][column - 2] = 0;
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            // go right and then inside
            if(helper == 2){
                mazeArr[depth - 2][row - 2][column - 1] = 0;
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            // go right and then down
            if(helper == 3){
                mazeArr[depth - 2][row - 2][column - 1] = 0;
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            // go inside and then right
            if(helper == 4){
                mazeArr[depth - 1][row - 2][column - 2] = 0;
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            // go inside and then down
            if(helper == 5){
                mazeArr[depth - 1][row - 2][column - 2] = 0;
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }


        }
        //Even depth, odd rows, even columns
        if(depth % 2 == 0 && row % 2 != 0 && column % 2 == 0){
            //fix last depth
            for(int i = 0;i < row; i++){
                for(int j = 0;j < column; j++){
                    mazeArr[depth-1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last column
            for(int i = 0;i < depth; i++){
                for(int j = 0;j < row - 1; j++){
                    mazeArr[i][j][column-1] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 2);
            if(helper == 0){
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            if(helper == 1){
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
        }

        mazeArr[depth-1][row-1][column-1] = 0;
        Maze3D newMaze = new Maze3D(mazeArr);
        return newMaze;
    }
}