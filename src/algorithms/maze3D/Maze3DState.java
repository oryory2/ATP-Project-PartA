package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState /** This Class describe a State inside a 3DMaze (Position on the Maze board) */
{
        // We chose to describe illegal Maze3DState by having position {-1,-1,-1}, illegal means that it's a wall or it's got indexes that are out of range

     /**
     * constructor
     * @param p the Position3D that represent the Maze3DState
     * assign p to this.pose field
     */

        public Maze3DState(Position3D p)
        {
            if(p == null)
            {
                throw new RuntimeException("The Position3D that supplied is not legal (null)");
            }
            if((p.getDepthIndex() < 0) || (p.getRowIndex() < 0) || (p.getColumnIndex() < 0))
            {
                if(!((p.getDepthIndex() == -1) && (p.getRowIndex() == -1) && (p.getColumnIndex() == -1)))
                    throw new RuntimeException("The Position3D that supplied is not legal! Position3D can't have negative indexes");
            }
            this.pose = p;
        }

     /**
     * @return whether the Maze3DState is legal or not (boolean)
     */
        public boolean legalState()
        {
            if((((Position3D)this.pose).getDepthIndex() == -1) && (((Position3D)this.pose).getRowIndex() == -1) && (((Position3D)this.pose).getColumnIndex() == -1))
                return false;
            return true;
        }

     /**
     * Returns whether other Maze3DState is equal to this one
     * Compares between Maze3DStates by depth/row/column
     * @param state Maze3DState we want to compare to
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

            Position3D thisPose = ((Position3D)thisState);
            Position3D otherPose = ((Position3D)otherState);

            if((thisPose.getDepthIndex() == otherPose.getDepthIndex()) && (thisPose.getRowIndex() == otherPose.getRowIndex()) && (thisPose.getColumnIndex() == otherPose.getColumnIndex()))
                return true;
            return false;
        }

     /**
     * toString implementation of Maze3DStates
     * Uses the Position3D toString implementation in oreder to print the Maze3DStates
     * @return Maze3DState's Position toString (String)
     */
        public String toString()
        {
            return this.getState().toString();
        }
}
