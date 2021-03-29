package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable
        /**
         * This Class describe a Searchable Maze (Maze that can be resolved)
         */
{

    private Maze maze;

    public SearchableMaze(Maze maze)
    {
        this.maze = maze;
    }

    public ArrayList<AState> getAllPossibleStates(AState state) // gets a MazeState, and returns all the Possible legal moves in the Maze
    {
        ArrayList<AState> maze_state_arr = new ArrayList<AState>();
        boolean flag_up = false;
        boolean flag_down = false;
        boolean flag_left = false;
        boolean flag_right = false;
        for(int i = 0; i < 8; i++)
        {
            maze_state_arr.add(new MazeState(new Position(-1,-1))); // initializing the ArrayList, (-1,-1) means illegal move
        }
        int [][] mazeArr = this.maze.getMazeArr();
        int thisRow = ((Position)state.getState()).getRow();
        int thisColumn = ((Position)state.getState()).getColumn();


        if((((Position)state.getState()).getRow() - 1 >= 0) && (mazeArr[thisRow - 1][thisColumn] == 0))
        {
            Position p = new Position(((Position)state.getState()).getRow() - 1, ((Position)state.getState()).getColumn()); // upper
            maze_state_arr.set(0,new MazeState(p));
            flag_up = true;
        }

        if((((Position)state.getState()).getColumn() + 1 < this.maze.getMax_columns()) && (mazeArr[thisRow][thisColumn + 1] == 0))
        {
            Position p = new Position(((Position)state.getState()).getRow(), ((Position)state.getState()).getColumn() + 1); // right
            maze_state_arr.set(2,new MazeState(p));
            flag_right = true;
        }


        if((((Position)state.getState()).getRow() + 1 < this.maze.getMax_rows()) && (mazeArr[thisRow + 1][thisColumn] == 0))
        {
            Position p = new Position(((Position)state.getState()).getRow() + 1, ((Position)state.getState()).getColumn()); // lower
            maze_state_arr.set(4,new MazeState(p));
            flag_down = true;
        }

        if((((Position)state.getState()).getColumn() - 1 >= 0) && (mazeArr[thisRow][thisColumn - 1] == 0))
        {
            Position p  = new Position(((Position)state.getState()).getRow(), ((Position)state.getState()).getColumn() - 1); // left
            maze_state_arr.set(6,new MazeState(p));
            flag_left = true;
        }

        if((((Position)state.getState()).getRow() - 1 >= 0 && ((Position)state.getState()).getColumn() + 1 < this.maze.getMax_columns())  && (mazeArr[thisRow - 1][thisColumn + 1] == 0))
        { // upper right
            if(flag_up == true || flag_right == true){
                Position p = new Position(((Position)state.getState()).getRow() - 1, ((Position)state.getState()).getColumn() + 1);
                maze_state_arr.set(1,new MazeState(p));
            }
        }

        if((((Position)state.getState()).getRow() + 1 < this.maze.getMax_rows() && ((Position)state.getState()).getColumn() + 1 < this.maze.getMax_columns()) && (mazeArr[thisRow + 1][thisColumn + 1] == 0))
        { // lower right
            if(flag_down == true || flag_right == true){
                Position p = new Position(((Position)state.getState()).getRow() + 1, ((Position)state.getState()).getColumn() + 1);
                maze_state_arr.set(3,new MazeState(p));
            }
        }

        if((((Position)state.getState()).getRow() + 1 < this.maze.getMax_rows() && ((Position)state.getState()).getColumn() - 1 >= 0) && (mazeArr[thisRow + 1][thisColumn - 1] == 0))
        { // lower left
            if(flag_down == true || flag_left == true){
                Position p = new Position(((Position)state.getState()).getRow() + 1, ((Position)state.getState()).getColumn() - 1);
                maze_state_arr.set(5,new MazeState(p));
            }
        }

        if((((Position)state.getState()).getRow() - 1 >= 0 && ((Position)state.getState()).getColumn() - 1 >= 0) && (mazeArr[thisRow - 1][thisColumn - 1] == 0))
        { // upper left
            if(flag_up == true || flag_left == true){
                Position p = new Position(((Position)state.getState()).getRow() - 1, ((Position)state.getState()).getColumn() - 1);
                maze_state_arr.set(7,new MazeState(p));
            }
        }
        return maze_state_arr;
    }
}
