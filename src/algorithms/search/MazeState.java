package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState
        /**
         * This Class describe a State inside a Maze (Position on the Maze board)
         */
{
    // We chose to describe illegal MazeState by having position {-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

    public MazeState(Position p)
    {
        if(p == null)
        {
            throw new RuntimeException("The Position that supplied is not legal (null)");
        }
        if((p.getRow() < 0) ||(p.getColumn() < 0))
        {
            if(!((p.getRow() == -1) && (p.getColumn() == -1)))
                throw new RuntimeException("The Position that supplied is not legal! Position can't have negative indexes");
        }
        this.pose = p;
    }

    public Object getState()
    {
        return pose;
    }

    public boolean legalState()
    {
        if((((Position)this.pose).getRow() == -1) && (((Position)this.pose).getColumn() == -1))
            return false;
        return true;
    }

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

        if((thisPose.getRow() == otherPose.getRow()) && (thisPose.getColumn() == otherPose.getColumn()))
            return true;
        return false;
    }

    public String toString()
    {
        return this.getState().toString();
    }
}
