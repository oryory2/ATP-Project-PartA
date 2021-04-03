package algorithms.search;

import java.util.ArrayList;

public interface ISearchable /** This Interface describe a SearchAble problem */

{
     /**
     * gets an AState, and returns all the Possible moves in the specific problem
     * from the specific Astate
     * @param state The state from which we want to move forward
     * @return list of possible moves (ArrayList<AState>)
     */
    ArrayList<AState> getAllSuccessors(AState state); // gets a AState, and returns all the Possible legal moves in the specific problem

     /**
     * Arranges the list of legal moves in order
     * According to the priorities of the specific problem
     * @param state_List list of Successors
     * @return States list arranged by priority (ArrayList<AState>)
     */
    ArrayList<AState> getPriorityStates(ArrayList<AState> state_List);

     /**
     * @return The Start State of an ISearchable problem (AState)
     */
    AState getStartState();

     /**
     * @return The Goal State of an ISearchable problem (AState)
     */
    AState getGoalState();

     /**
     * check whether a certain state is visited
     * @param state the state we want to check if we visited in
     * @return Visited or not (boolean)
     */
    boolean isVisited(AState state);

     /**
     * Mark a certain state as visited
     * @param state the state we want to mark as visited
     */
    void setVisit(AState state);

     /**
     * reset the problem fields etc.
     * in case we want to solve it again
     */
    void resetProblem();

}
