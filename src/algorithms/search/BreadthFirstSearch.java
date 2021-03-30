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
        this.name = "BreadthFirstSearch";
    }

    public Solution solve(ISearchable ISC)
    {
        AState startState = ISC.getStartState();
        AState thisState = startState;
        int visitedStates = 0;
        LinkedList<AState> statesQueue = new LinkedList<AState>();
        //ArrayList<AState> visitedStates = new ArrayList<AState>();
        statesQueue.add(startState);
        //visitedStates.add(startState);
        ISC.setVisit(startState);
        visitedStates++;
        if (thisState.compStates(ISC.getGoalState()))
            return restoreSolutionPath(ISC.getStartState(), thisState);


        boolean solved = false;
        while((!statesQueue.isEmpty()) && (!solved))
        {
            statesQueue.remove(thisState);
            ArrayList<AState> nearbyStates = ISC.getAllPossibleStates(thisState);
            for(int i = 0; i < nearbyStates.size(); i++)
            {
                if (!(nearbyStates.get(i).legalState()))
                    continue;
                //if (ASearchingAlgorithm.isVisited(visitedStates, nearbyStates.get(i)))
                if(ISC.isVisited(nearbyStates.get(i)))
                    continue;
                nearbyStates.get(i).setPrevState(thisState);
                statesQueue.add(nearbyStates.get(i));
                ISC.setVisit(nearbyStates.get(i));
                visitedStates++;
                //visitedStates.add(nearbyStates.get(i));

                if (nearbyStates.get(i).compStates(ISC.getGoalState()))
                {
                    thisState = nearbyStates.get(i);
                    solved = true;
                    break;
                }
            }
            if(solved)
                break;
            thisState = statesQueue.get(0);
        }   this.NumberOfNodesEvaluated = visitedStates;
            return restoreSolutionPath(ISC.getStartState(), thisState);
    }
}
