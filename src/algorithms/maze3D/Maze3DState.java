package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState
{
        // We chose to describe illegal Maze3DState by having position {-1,-1,-1}, illegal means that it's a wall or it's got indexes that are out of range
        public Maze3DState(Position3D p)
        {
            if(p == null)
            {
                throw new RuntimeException("The Position3D that supplied is not legal (null)");
            }
            if((p.getDepth() < 0) || (p.getRow() < 0) || (p.getColumn() < 0))
            {
                if(!((p.getDepth() == -1) && (p.getRow() == -1) && (p.getColumn() == -1)))
                    throw new RuntimeException("The Position3D that supplied is not legal! Position3D can't have negative indexes");
            }
            this.pose = p;
        }

        public Object getState()
        {
            return pose;
        }

        public boolean legalState()
        {
            if((((Position3D)this.pose).getDepth() == -1) && (((Position3D)this.pose).getRow() == -1) && (((Position3D)this.pose).getColumn() == -1))
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
