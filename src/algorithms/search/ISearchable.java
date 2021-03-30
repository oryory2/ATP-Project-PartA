package algorithms.search;

import java.util.ArrayList;

public interface ISearchable
        /**
         * This Interface describe a SearchAble problem
         */
{
    ArrayList<AState> getAllPossibleStates(AState state); // gets a AState, and returns all the Possible legal moves in the specific problem
    AState getStartState();
    AState getGoalState();
    public boolean isVisited(AState state);
    void setVisit(AState state);
    void resetProblem();
    ArrayList<AState> getPriorityStates(ArrayList<AState> state_List);

}
