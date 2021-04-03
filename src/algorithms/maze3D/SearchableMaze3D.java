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
        if(maze == null)
        {
            throw new RuntimeException("The Maze3D that supplied is not legal (null)");
        }
        if((maze.getMax_depth() <= 1) || (maze.getMax_rows() <= 1) || (maze.getMax_columns() <= 1))
        {
            throw new RuntimeException("The Maze3D that supplied is not legal! Depth/Row/Column values must be at least 2");
        }
        this.maze = maze;
        this.visitArr = new int[maze.getMax_depth()][maze.getMax_rows()][maze.getMax_columns()];
    }

    public ArrayList<AState> getAllSuccessors(AState state)
    {
        if(state == null)
        {
            throw new RuntimeException("The AState that supplied is not legal! (null)");
        }
        ArrayList<AState> maze_state_arr = new ArrayList<AState>();
        for(int i = 0; i < 6; i++)
        {
            maze_state_arr.add(new Maze3DState((new Position3D(-1,-1,-1)))); // initializing the ArrayList, (-1,-1,-1) means illegal move
        }
        int [][][] mazeArr = this.maze.getMap();
        int thisDepth = ((Position3D)state.getState()).getDepth();
        int thisRow = ((Position3D)state.getState()).getRow();
        int thisColumn = ((Position3D)state.getState()).getColumn();

        if((thisDepth < 0) || (thisRow < 0)|| (thisColumn < 0))
        {
            if(!((thisDepth == -1) && (thisRow == -1) && (thisColumn == -1)))
                throw new RuntimeException("The AState that supplied is not legal! AState Position3D can't have negative indexes");
        }

        // for each possible move (up, down, left, right, inside, outside) we check if it's a valid move
        if((thisRow - 1 >= 0) && (mazeArr[thisDepth][thisRow - 1][thisColumn] == 0))
        {
            Position3D p = new Position3D(thisDepth,thisRow - 1, thisColumn); // upper
            maze_state_arr.set(0,new Maze3DState(p));
        }

        if((thisColumn + 1 < this.maze.getMax_columns()) && (mazeArr[thisDepth][thisRow][thisColumn + 1] == 0))
        {
            Position3D p = new Position3D(thisDepth,thisRow, thisColumn + 1); // right
            maze_state_arr.set(1,new Maze3DState(p));
        }

        if((thisRow + 1 < this.maze.getMax_rows()) && (mazeArr[thisDepth][thisRow + 1][thisColumn] == 0))
        {
            Position3D p = new Position3D(thisDepth,thisRow + 1, thisColumn); // lower
            maze_state_arr.set(2,new Maze3DState(p));
        }

        if((thisColumn - 1 >= 0) && (mazeArr[thisDepth][thisRow][thisColumn - 1] == 0))
        {
            Position3D p = new Position3D(thisDepth,thisRow, thisColumn - 1); // left
            maze_state_arr.set(3,new Maze3DState(p));
        }

        if((thisDepth + 1 < this.maze.getMax_depth()) && (mazeArr[thisDepth + 1][thisRow][thisColumn] == 0))
        {
            Position3D p = new Position3D(thisDepth + 1,thisRow, thisColumn); // inside (+1 depth)
            maze_state_arr.set(4,new Maze3DState(p));
        }

        if((thisDepth - 1 >= 0) && (mazeArr[thisDepth - 1][thisRow][thisColumn] == 0))
        {
            Position3D p = new Position3D(thisDepth - 1,thisRow, thisColumn); // outside (-1 depth)
            maze_state_arr.set(5,new Maze3DState(p));
        }
        return maze_state_arr;
    }


    public ArrayList<AState> getPriorityStates(ArrayList<AState> state_List)
    {
        if(state_List == null)
        {
            throw new RuntimeException("The ArrayList that supplied is not legal! (null)");
        }
        // there is no need for this Method in the 3DMaze, so we just returned the original state_List
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
        if(state == null)
        {
            throw new RuntimeException("The AState that supplied is not legal! (null)");
        }
        Position3D thisState = ((Position3D)state.getState());
        if(this.visitArr[thisState.getDepth()][thisState.getRow()][thisState.getColumn()] == 1)
            return true;
        return false;
    }

    public void setVisit(AState state)
    {
        if(state == null)
        {
            throw new RuntimeException("The AState that supplied is not legal! (null)");
        }
        Position3D thisState = ((Position3D)state.getState());
        this.visitArr[thisState.getDepth()][thisState.getRow()][thisState.getColumn()] = 1;
    }

    public void resetProblem()
    {
        this.visitArr = new int[maze.getMax_depth()][maze.getMax_rows()][maze.getMax_columns()];
    }
}
