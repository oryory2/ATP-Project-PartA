package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MyMaze3DGenerator extends AMaze3DGenerator /** This Class describe 3DMazeGenerator that build the Maze by the DFS method */
{

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

        mazeArr[0][0][0] = 0; //start position

        ArrayList<Position3D> FrontVal1 = new ArrayList<Position3D>();  // array of Positions 2 (1 in depth) steps away from thisPose, with value 1
        Position3D thisPose = new Position3D(0, 0, 0);
        Position3D[] startPoseNeighbors = Position3D.findLegalNeighbors(thisPose, depth, row, column);
        for (int i = 0; i < 6; i++) {
            if (startPoseNeighbors[i] == null)
                break;
            if ((mazeArr[startPoseNeighbors[i].getRow()][startPoseNeighbors[i].getRow()][startPoseNeighbors[i].getColumn()] == 1)) {
                FrontVal1.add(startPoseNeighbors[i]);
            }
        }

        while (!(FrontVal1.isEmpty())) {
            int randomPick = (int) (Math.random() * (FrontVal1.size()));
            Position3D randFront = FrontVal1.get(randomPick); // choose a random Wall
            ArrayList<Position3D> BackVal0 = new ArrayList<Position3D>(); // array of Positions 2 (1 in depth) steps away from randFront, with value 0
            Position3D[] randFrontNeighbors = Position3D.findLegalNeighbors(randFront, depth, row, column);
            for (int i = 0; i < 6; i++) {
                if (randFrontNeighbors[i] == null)
                    break;
                if ((mazeArr[randFrontNeighbors[i].getDepth()][randFrontNeighbors[i].getRow()][randFrontNeighbors[i].getColumn()] == 0))
                    BackVal0.add(randFrontNeighbors[i]);
                if ((mazeArr[randFrontNeighbors[i].getDepth()][randFrontNeighbors[i].getRow()][randFrontNeighbors[i].getColumn()] == 1))
                    FrontVal1.add(randFrontNeighbors[i]);
            }
            int randomNei = (int) (Math.random() * (BackVal0.size()));
            Position3D randBack = BackVal0.get(randomNei); // choose a random Position from randNeighbor and change the value of the cell between them and the randFront to 0
            updatePositionsVal(randFront, randBack, mazeArr); // updating the values of the wanted Positions

            FrontVal1.remove(randFront);
        }
        FinishMaze(mazeArr);
        mazeArr[depth-1][row-1][column-1] = 0;
        Maze3D newMaze = new Maze3D(mazeArr);
        return newMaze;
    }
    private void updatePositionsVal (Position3D front, Position3D back, int [][][] mazeArr)
    {
        if (front.getRow() == back.getRow() && front.getColumn() == back.getColumn()) {
            mazeArr[front.getDepth()][front.getRow()][front.getColumn()] = 0;
        }
        //The rows and depth are equal - we will change the columns
        if (front.getRow() == back.getRow() && front.getDepth() == back.getDepth()) {
            mazeArr[front.getDepth()][front.getRow()][front.getColumn()] = 0;
            mazeArr[front.getDepth()][front.getRow()][(front.getColumn() + back.getColumn()) / 2] = 0;
        }
        //The depth and columns are equal - we will change the rows
        if (front.getDepth() == back.getDepth() && front.getColumn() == back.getColumn()) {
            mazeArr[front.getDepth()][front.getRow()][front.getColumn()] = 0;
            mazeArr[front.getDepth()][(front.getRow() + back.getRow()) / 2][front.getColumn()] = 0;
        }
    }

    private void FinishMaze(int [][][] mazeArr) {
        //Odd depth, odd rows, odd columns -> normal situation dont do anything
        // ------------------------- //
        int depth = mazeArr.length;
        int row = mazeArr[0].length;
        int column = mazeArr[0][0].length;

        //Odd depth, odd rows, even columns
        if (depth % 2 != 0 && row % 2 != 0 && column % 2 == 0) {
            //fix last column
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < row - 1; j++) {
                    mazeArr[i][j][column - 1] = (int) (Math.random() * 2);
                }
            }
        }
        //Odd depth, even rows, odd columns
        if (depth % 2 != 0 && row % 2 == 0 && column % 2 != 0) {
            //fix last row
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < column - 1; j++) {
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
        }
        //Odd depth, even rows, even columns
        if (depth % 2 != 0 && row % 2 == 0 && column % 2 == 0) {
            //fix last column
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < row - 1; j++) {
                    mazeArr[i][j][column - 1] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < column - 1; j++) {
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 2);
            if (helper == 0) {
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            if (helper == 1) {
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
        }
        //Even depth, odd rows, odd columns
        if (depth % 2 == 0 && row % 2 != 0 && column % 2 != 0) {
            //fix last depth
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    mazeArr[depth - 1][i][j] = (int) (Math.random() * 2);
                }
            }
        }
        //Even depth, even rows, odd columns
        if (depth % 2 == 0 && row % 2 == 0 && column % 2 != 0) {
            //fix last depth
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    mazeArr[depth - 1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < column - 1; j++) {
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }

            int helper = (int) (Math.random() * 2);
            if (helper == 0) {
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            if (helper == 1) {
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }

        }
        //Even depth, even rows, even columns
        if (depth % 2 == 0 && row % 2 == 0 && column % 2 == 0) {
            //fix last depth
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    mazeArr[depth - 1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last row
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < column - 1; j++) {
                    mazeArr[i][row - 1][j] = (int) (Math.random() * 2);
                }
            }
            //fix last column
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < row - 1; j++) {
                    mazeArr[i][j][column - 1] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 6);
            //go down and then inside
            if (helper == 0) {
                mazeArr[depth - 2][row - 1][column - 2] = 0;
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
            //go down and then right
            if (helper == 1) {
                mazeArr[depth - 2][row - 1][column - 2] = 0;
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            // go right and then inside
            if (helper == 2) {
                mazeArr[depth - 2][row - 2][column - 1] = 0;
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            // go right and then down
            if (helper == 3) {
                mazeArr[depth - 2][row - 2][column - 1] = 0;
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            // go inside and then right
            if (helper == 4) {
                mazeArr[depth - 1][row - 2][column - 2] = 0;
                mazeArr[depth - 1][row - 2][column - 1] = 0;
            }
            // go inside and then down
            if (helper == 5) {
                mazeArr[depth - 1][row - 2][column - 2] = 0;
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
        }
        //Even depth, odd rows, even columns
        if (depth % 2 == 0 && row % 2 != 0 && column % 2 == 0) {
            //fix last depth
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    mazeArr[depth - 1][i][j] = (int) (Math.random() * 2);
                }
            }
            //fix last column
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < row - 1; j++) {
                    mazeArr[i][j][column - 1] = (int) (Math.random() * 2);
                }
            }
            int helper = (int) (Math.random() * 2);
            if (helper == 0) {
                mazeArr[depth - 2][row - 1][column - 1] = 0;
            }
            if (helper == 1) {
                mazeArr[depth - 1][row - 1][column - 2] = 0;
            }
        }
    }
}