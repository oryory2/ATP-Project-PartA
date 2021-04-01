package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState
{
        private Position3D pose;

        public Maze3DState(Position3D p)
        {
            this.pose = p;
        }

        public Object getState()
        {
            return pose;
        }

        public boolean legalState()
        {
            if((this.pose.getDepth() == -1) &&(this.pose.getRow() == -1) && (this.pose.getColumn() == -1))
                return false;
            return true;
        }

        public boolean compStates(AState state)
        {
            Object thisState = this.getState();
            Object otherState = state.getState();

            Position3D thisPose = ((Position3D)thisState);
            Position3D otherPose = ((Position3D)otherState);

            if((thisPose.getDepth() == otherPose.getDepth()) && (thisPose.getRow() == otherPose.getRow()) && (thisPose.getColumn() == otherPose.getColumn()))
                return true;
            return false;
        }

        public String toString()
        {
            return this.getState().toString();
        }
}
