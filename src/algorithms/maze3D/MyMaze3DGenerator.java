package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MyMaze3DGenerator extends AMaze3DGenerator /** This Class describe 3DMazeGenerator that build the Maze by the DFS method */
{

    /**
     * Returns a new Maze3D build by the Prim algorithm
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @param depth The depth of the new maze
     * @return the new maze3D (Maze3D)
     */
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
        Position3D[] startPoseNeighbors = findLegalNeighbors(thisPose, depth, row, column);
        for (int i = 0; i < 6; i++) {
            if (startPoseNeighbors[i] == null)
                break;
            if ((mazeArr[startPoseNeighbors[i].getDepthIndex()][startPoseNeighbors[i].getRowIndex()][startPoseNeighbors[i].getColumnIndex()] == 1)) {
                FrontVal1.add(startPoseNeighbors[i]);
            }
        }

        while (!(FrontVal1.isEmpty())) {
            int randomPick = (int) (Math.random() * (FrontVal1.size()));
            Position3D randFront = FrontVal1.get(randomPick); // choose a random Wall
            ArrayList<Position3D> BackVal0 = new ArrayList<Position3D>(); // array of Positions 2 (1 in depth) steps away from randFront, with value 0
            Position3D[] randFrontNeighbors = findLegalNeighbors(randFront, depth, row, column);
            for (int i = 0; i < 6; i++) {
                if (randFrontNeighbors[i] == null)
                    break;
                if ((mazeArr[randFrontNeighbors[i].getDepthIndex()][randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 0))
                    BackVal0.add(randFrontNeighbors[i]);
                if ((mazeArr[randFrontNeighbors[i].getDepthIndex()][randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 1))
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


    /**
     * gets a Position3D, and returns all the Possible legal moves (Position3D) from
     * the specific Position3D by the depth/row/columns borders
     * @param p The Specific Position
     * @param max_depth The depth of the maze
     * @param max_row The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return List of possible Positions (Position3D[])
     */
    private Position3D[] findLegalNeighbors (Position3D p, int max_depth, int max_row, int max_column)
    {
        if(p == null)
        {
            throw new RuntimeException("The Position3D that supplied is not legal (null)");
        }
        if((p.getDepthIndex() < 0) || (p.getRowIndex() < 0) || (p.getColumnIndex() < 0))
        {
            if(!((p.getDepthIndex() == -1) && (p.getRowIndex() == -1) && (p.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position3D indexes are not legal! Position3D can't have negative indexes");
        }

        int NeighborsCounter = 0;
        Position3D [] poseArr = new Position3D[6];

        // for each possible move (up, down, left, right, inside, outside) we check if it's a valid move
        if(p.getRowIndex() - 2 >= 0) // up
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex(), p.getRowIndex() - 2, p.getColumnIndex());
            NeighborsCounter++;
        }
        if(p.getColumnIndex() + 2 < max_column) // right
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex() + 2);
            NeighborsCounter++;
        }
        if(p.getRowIndex() + 2 < max_row) // down
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex(), p.getRowIndex() + 2, p.getColumnIndex());
            NeighborsCounter++;
        }
        if(p.getColumnIndex() - 2 >= 0) // left
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex() - 2);
            NeighborsCounter++;
        }
        if(p.getDepthIndex() + 2 < max_depth) // inside
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex() + 2, p.getRowIndex(), p.getColumnIndex());
            NeighborsCounter++;
        }
        if(p.getDepthIndex() - 2 >= 0) // outside
        {
            poseArr[NeighborsCounter] = new Position3D(p.getDepthIndex() - 2, p.getRowIndex(), p.getColumnIndex());
        }
        return poseArr;
    }

     /**
     * Update the values of 2 Positions:
     * 1. Back Position3D
     * 2. the Position3D between the Front and the Back
     * @param front The "front" Position3D
     * @param back The "back" Position3D
     * @param mazeArr The 3D Array representing the Maze
     */
    private void updatePositionsVal (Position3D front, Position3D back, int [][][] mazeArr)
    {
        if (front.getRowIndex() == back.getRowIndex() && front.getColumnIndex() == back.getColumnIndex()) {
            mazeArr[front.getDepthIndex()][front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[(front.getDepthIndex() + back.getDepthIndex()) / 2][front.getRowIndex()][front.getColumnIndex()] = 0;
        }
        //The rows and depth are equal - we will change the columns
        if (front.getRowIndex() == back.getRowIndex() && front.getDepthIndex() == back.getDepthIndex()) {
            mazeArr[front.getDepthIndex()][front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[front.getDepthIndex()][front.getRowIndex()][(front.getColumnIndex() + back.getColumnIndex()) / 2] = 0;
        }
        //The depth and columns are equal - we will change the rows
        if (front.getDepthIndex() == back.getDepthIndex() && front.getColumnIndex() == back.getColumnIndex()) {
            mazeArr[front.getDepthIndex()][front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[front.getDepthIndex()][(front.getRowIndex() + back.getRowIndex()) / 2][front.getColumnIndex()] = 0;
        }
    }

     /**
     * In cases of different Row/Columns combinations,
     * we Make sure there will be a Path to the GoalState
     * @param mazeArr The 3D Array representing the Maze
     */
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

        //Even depth, even rows, odd columns
        if (depth % 2 == 0 && row % 2 == 0 && column % 2 != 0) {
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