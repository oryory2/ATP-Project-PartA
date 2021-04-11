package algorithms.mazeGenerators;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyMazeGenerator extends AMazeGenerator /** This Class describe MazeGenerator that build the Maze by the DFS method */

{
     /**
     * Returns a new Maze build by the Prim algorithm
     * @param row The number of rows will be in the maze
     * @param column The number of columns will be in the maze
     * @return the new maze (Maze)
     */
    public Maze generate(int row, int column) {
        if ((row <= 1) || (column <= 1)) {
            throw new RuntimeException("One or more of the supplied sizes are not legal! Row and Column values must be at least 2");
        }
        if(row == 2 && column == 2)
            return generate2on2();
        int[][] mazeArr = new int[row][column];
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++)
                mazeArr[i][j] = 1;
        }

        mazeArr[0][0] = 0; //start position
        ArrayList<Position> FrontVal1 = new ArrayList<Position>(); // array of Positions 2 steps away from thisPose, with value 1
        Position thisPose = new Position(0, 0);
        Position[] startPoseNeighbors = findLegalNeighbors(thisPose, mazeArr.length, mazeArr[0].length);
        for (int i = 0; i < 4; i++) {
            if (startPoseNeighbors[i] == null)
                break;
            if ((mazeArr[startPoseNeighbors[i].getRowIndex()][startPoseNeighbors[i].getColumnIndex()] == 1))
                FrontVal1.add(startPoseNeighbors[i]);
        }

        while (!(FrontVal1.isEmpty())) {
            int randomPick = (int) (Math.random() * (FrontVal1.size()));
            Position randFront = FrontVal1.get(randomPick); // choose a random Wall
            ArrayList<Position> BackVal0 = new ArrayList<Position>(); // array of Positions 2 steps away from randFront, with value 0
            Position[] randFrontNeighbors = findLegalNeighbors(randFront, mazeArr.length, mazeArr[0].length);
            for (int i = 0; i < 4; i++) {
                if (randFrontNeighbors[i] == null)
                    break;
                if ((mazeArr[randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 0))
                    BackVal0.add(randFrontNeighbors[i]);
                if ((mazeArr[randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 1))
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

    /**
     * gets a Position, and returns all the Possible legal moves (Positions) from
     * the specific Position by the row and columns borders
     *
     * @param p          The Specific Position
     * @param max_row    The number of rows in the maze
     * @param max_column The number of columns in the maze
     * @return List of possible Positions (Position[])
     */
    public static Position[] findLegalNeighbors(Position p, int max_row, int max_column) // gets a Position, and returns all the Possible moves (Positions) in the Maze
    {
        if (p == null) {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if ((p.getRowIndex() < 0) || (p.getColumnIndex() < 0)) {
            if (!((p.getRowIndex() == -1) && (p.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }
        if((max_row <= 1) || (max_column <= 1))
        {
            throw new RuntimeException("The row/columns indexes are invalid (must be at least 2)");
        }

        // for each possible move (up, down, left, right) we check if it's a valid move
        int NeighborsCounter = 0;
        Position[] poseArr = new Position[4];
        if (p.getRowIndex() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(p.getRowIndex() - 2, p.getColumnIndex()); // upper
            NeighborsCounter++;
        }
        if (p.getRowIndex() + 2 < max_row) {
            poseArr[NeighborsCounter] = new Position(p.getRowIndex() + 2, p.getColumnIndex()); // lower
            NeighborsCounter++;
        }
        if (p.getColumnIndex() + 2 < max_column) {
            poseArr[NeighborsCounter] = new Position(p.getRowIndex(), p.getColumnIndex() + 2); // right
            NeighborsCounter++;
        }
        if (p.getColumnIndex() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(p.getRowIndex(), p.getColumnIndex() - 2); // left
        }
        return poseArr;
    }

    /**
     * Update the values of 2 Positions:
     * 1. Back Position
     * 2. the Position between the Front and the Back
     * @param front The "front" Position
     * @param back The "back" Position
     * @param mazeArr The 2D Array representing the Maze
     */
    private void updatePositionsVal (Position front, Position back, int [][] mazeArr)
    {
        if((front == null) || (back == null) || (mazeArr == null))
        {
            throw new RuntimeException("One of the Arguments supplied is not legal (null)");
        }
        if((front.getRowIndex() < 0) || (front.getColumnIndex() < 0))
        {
            if(!((front.getRowIndex() == -1) && (front.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }
        if((back.getRowIndex() < 0) || (back.getColumnIndex() < 0))
        {
            if(!((back.getRowIndex() == -1) && (back.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }

        if (front.getRowIndex() == back.getRowIndex()) {
            mazeArr[front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[front.getRowIndex()][(front.getColumnIndex() + back.getColumnIndex()) / 2] = 0;
        }
        if (front.getColumnIndex() == back.getColumnIndex()) {
            mazeArr[front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[(front.getRowIndex() + back.getRowIndex()) / 2][front.getColumnIndex()] = 0;
        }
    }

     /**
     * In cases of different Row/Columns combinations,
     * we Make sure there will be a Path to the GoalState
     * @param mazeArr The 2D Array representing the Maze
     */
    private void FinishMaze(int [][] mazeArr)
    {
        if(mazeArr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
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

     /**
     * In a case the Row=Column=2, we Generate the maze ourself
     * @return the new maze (Maze)
     */
    public Maze generate2on2(){
        int[][] mazeArr = new int[2][2];
        int randomizeMaze = (int) (Math.random() * 2);
        if(randomizeMaze == 0){
            mazeArr[1][0] = 1;
        }
        if(randomizeMaze == 1){
            mazeArr[0][1] = 1;
        }
        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }
}