package algorithms.search;

import java.util.ArrayList;

public interface ISearchable
        /**
         * This Interface describe a SearchAble problem
         */
{
    ArrayList<AState> getAllSuccessors(AState state); // gets a AState, and returns all the Possible legal moves in the specific problem
    ArrayList<AState> getPriorityStates(ArrayList<AState> state_List);
    AState getStartState();
    AState getGoalState();
    boolean isVisited(AState state);
    void setVisit(AState state);
    void resetProblem();

}
