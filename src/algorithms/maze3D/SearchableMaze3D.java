package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;
import algorithms.search.SearchableMaze;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable
{
    private Maze3D maze;
    private int [][][] visitArr;

    public SearchableMaze3D(Maze3D maze)
    {
        this.maze = maze;
        this.visitArr = new int[maze.getMax_depth()][maze.getMax_rows()][maze.getMax_columns()];
    }

    public ArrayList<AState> getAllPossibleStates(AState state)
    {
        ArrayList<AState> maze_state_arr = new ArrayList<AState>();

        for(int i = 0; i < 6; i++)
        {
            maze_state_arr.add(new Maze3DState((new Position3D(-1,-1,-1)))); // initializing the ArrayList, (-1,-1,-1) means illegal move
        }
        int [][][] mazeArr = this.maze.getMap();
        int thisDepth = ((Position3D)state.getState()).getDepth();
        int thisRow = ((Position3D)state.getState()).getRow();
        int thisColumn = ((Position3D)state.getState()).getColumn();

        if((((Position3D)state.getState()).getRow() - 1 >= 0) && (mazeArr[thisRow - 1][thisColumn] == 0))
        {
            Position p = new Position3D(((Position3D)state.getState()).getRow() - 1, ((Position3D)state.getState()).getColumn()); // upper
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

    public ArrayList<AState> getPriorityStates(ArrayList<AState> state_List)
    {
        return state_List;
    }

    public AState getStartState()
    {
        Position3D p = this.maze.getStartPosition();
        AState startState = new Maze3DState(p);
        return startState;
    }

    public AState getGoalState()
    {
        Position3D p = this.maze.getGoalPosition();
        AState goalState = new Maze3DState(p);
        return goalState;
    }

    public boolean isVisited(AState state)
    {
        Position3D thisState = ((Position3D)state.getState());
        if(this.visitArr[thisState.getDepth()][thisState.getRow()][thisState.getColumn()] == 1)
            return true;
        return false;
    }

    public void setVisit(AState state)
    {
        Position3D thisState = ((Position3D)state.getState());
        this.visitArr[thisState.getDepth()][thisState.getRow()][thisState.getColumn()] = 1;
    }

    public void resetProblem()
    {
        this.visitArr = new int[maze.getMax_depth()][maze.getMax_rows()][maze.getMax_columns()];
    }
}
