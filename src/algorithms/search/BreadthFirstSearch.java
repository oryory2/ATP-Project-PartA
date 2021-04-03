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
        this.setName("BreadthFirstSearch");

    }

    public Solution solve(ISearchable ISC)
    {
        if(ISC == null)
        {
            throw new RuntimeException("The ISearchable that supplied is not legal (null)");
        }
        AState startState = ISC.getStartState();
        AState thisState = startState;
        int visitedStates = 0; // counting how many States were visited (NumberOfNodesEvaluated)
        LinkedList<AState> statesQueue = new LinkedList<AState>();
        statesQueue.add(startState); // adding the startState to the queue
        ISC.setVisit(startState);
        visitedStates++;
        if (thisState.compStates(ISC.getGoalState())) // check if we are in the GoalState
        {
            this.setNumberOfNodesEvaluated(visitedStates);
            return restoreSolutionPath(ISC.getStartState(), thisState);
        }


        boolean solved = false;
        while((!statesQueue.isEmpty()) && (!solved))
        {
            // in each iteration we:
            // remove "thisState" from the queue
            // add all the Possible states we can move to, also set their "father" to be "thisState"
            statesQueue.remove(thisState);
            ArrayList<AState> nearbyStates = ISC.getAllSuccessors(thisState);
            for(int i = 0; i < nearbyStates.size(); i++)
            {
                if (!(nearbyStates.get(i).legalState()))
                    continue;
                if(ISC.isVisited(nearbyStates.get(i)))
                    continue;
                nearbyStates.get(i).setPrevState(thisState);
                statesQueue.add(nearbyStates.get(i));
                ISC.setVisit(nearbyStates.get(i));
                visitedStates++;

                if (nearbyStates.get(i).compStates(ISC.getGoalState())) // check if we are in the GoalState
                {
                    thisState = nearbyStates.get(i);
                    solved = true;
                    break;
                }
            }
            if(solved)
                break;
            thisState = statesQueue.get(0);
        }
        this.setNumberOfNodesEvaluated(visitedStates);
        ISC.resetProblem();
        return restoreSolutionPath(ISC.getStartState(), thisState); // restore the solution path and return the Solution to the problem
    }
}
