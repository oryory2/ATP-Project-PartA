package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm
        /**
         * This Class describe a Searching Algorithm work by the BreadthFirstSearch method
         */
{
    public BreadthFirstSearch()
    {
        super();
        super.name = "BreadthFirstSearch";
    }

    public Solution solve(ISearchable ISC)
    {
        AState startState = ISC.getStartState();
        AState thisState = startState;

        LinkedList<AState> statesQueue = new LinkedList<AState>();
        ArrayList<AState> visitedStates = new ArrayList<AState>();
        statesQueue.add(startState);
        visitedStates.add(startState);
        boolean solution = false;

        while((!statesQueue.isEmpty()) && (solution == false))
        {
            statesQueue.remove(thisState);
            ArrayList<AState> nearbyStates = ISC.getAllPossibleStates(thisState);
            for(int i = 0; i < nearbyStates.size(); i++)
            {
                if (!(nearbyStates.get(i).legalState()))
                    continue;
                if (ASearchingAlgorithm.isVisited(visitedStates, nearbyStates.get(i)) == true)
                    continue;
                nearbyStates.get(i).setPrevState(thisState);
                statesQueue.add(nearbyStates.get(i));
                if (nearbyStates.get(i).compStates(ISC.getGoalState()) == true)
                {
                    thisState = nearbyStates.get(i);
                    solution = true;
                }
            }
            thisState = statesQueue.get(0);
        }
            return restoreSolutionPath(ISC.getStartState(), thisState);
    }
}
