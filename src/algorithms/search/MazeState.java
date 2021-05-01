package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;

public class MazeState extends AState /** This Class describe a State inside a Maze (Position on the Maze board) */

{
    // We chose to describe illegal MazeState by having position {-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

     /**
     * constructor
     * @param p the position that represent the Mazestate
     * assign p to this.pose field
     */
    public MazeState(Position p)
    {
        if(p == null)
        {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if((p.getRowIndex() < 0) ||(p.getColumnIndex() < 0))
        {
            if(!((p.getRowIndex() == -1) && (p.getColumnIndex() == -1)))
                throw new RuntimeException("The Position that supplied is not legal! Position can't have negative indexes");
        }
        this.pose = p;
    }

     /**
     * @return whether the MazeState is legal or not (boolean)
     */
    public boolean legalState()
    {
        if((((Position)this.pose).getRowIndex() == -1) && (((Position)this.pose).getColumnIndex() == -1))
            return false;
        return true;
    }

     /**
     * Returns whether other MazeState is equal to this one
     * Compares between MazeStates by row and column
     * @param state MazeState we want to compare to
     * @return equal or not (boolean)
     */
    public boolean compStates(AState state)
    {
        if(state == null)
        {
            throw new RuntimeException("The AState that supplied is not legal! (null)");
        }
        Object thisState = this.getState();
        Object otherState = state.getState();

        Position thisPose = ((Position)thisState);
        Position otherPose = ((Position)otherState);

        if((thisPose.getRowIndex() == otherPose.getRowIndex()) && (thisPose.getColumnIndex() == otherPose.getColumnIndex()))
            return true;
        return false;
    }

     /**
     * toString implementation of MazeState
     * Uses the Position toString implementation in oreder to print the MazeState
     * @return MazeState's Position toString (String)
     */
    public String toString()
    {
        return this.getState().toString();
    }
}
