package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState
        /**
         * This Class describe a State inside a Maze (Position on the Maze board)
         */
{
    private Position pose;

    public MazeState(Position p)
    {
        this.pose = p;
    }

    public Object getState()
    {
        return pose;
    }

    public boolean legalState()
    {
        if((this.pose.getColumn() == -1) && (this.pose.getRow() == -1))
            return false;
        return true;
    }

    public boolean compStates(AState state)
    {
        Object thisState = this.getState();
        Object otherState = ((MazeState)state).getState();

        Position thisPose = ((Position)thisState);
        Position otherPose = ((Position)otherState);

        if((thisPose.getRow() == otherPose.getRow()) && (thisPose.getColumn() == otherPose.getColumn()))
            return true;
        return false;
    }

    public String toString() ////////////////////////////////////////////////////// optional(?)
    {
        return this.getState().toString();
    }
}
