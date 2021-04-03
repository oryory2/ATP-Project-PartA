package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm /** This Class describe a Searching Algorithm work by the DepthFirstSearch method */

{
    public DepthFirstSearch()
    {
        super();
        this.setName("DepthFirstSearch");
    }

     /**
     * This method solves an Isearchable problem using the Depth First Search algorithm
     * begins to solve from the starting point
     * Uses an array that saves all the states we visited
     * we will keep going in depth randomly
     * until there are no more moves
     * When there are no moves we will go back to the prev state.
     * When the goal is reached return the solution
     * @param ISC problem we want to solve
     * @return Solution of an Isearchable problem
     */
    public Solution solve(ISearchable ISC)
    {
        if(ISC == null)
        {
            throw new RuntimeException("The ISearchable that supplied is not legal (null)");
        }
        AState startState = ISC.getStartState();
        AState thisState = startState;
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        int visitedStates = 0; // counting how many States were visited (NumberOfNodesEvaluated)
        solutionPath.add(startState); // adding the startState to solutionPath
        ISC.setVisit(startState);
        visitedStates++;
        boolean End = false;

        while(!End)
        {
            // in each iteration we:
            // remove "thisState" from the solutionPath
            // gets all the optional move from the current State
            // if there is no legal move, go back for the last move before the current one

            if(thisState.compStates(ISC.getGoalState())) // check if we are in the GoalState
            {
                End = true;
                continue;
            }
            ArrayList<AState> possibleMoves = ISC.getAllSuccessors(thisState);
            boolean Moves = false;
            int counter = possibleMoves.size();
            int [] possibleMovesArr = new int[possibleMoves.size()];
            while (counter != 0)
            {
                int i = (int)(Math.random() * possibleMoves.size());
                if(possibleMovesArr[i] == 1)
                    continue;
                possibleMovesArr[i] = 1;
                counter--;

                if(!(possibleMoves.get(i).legalState()))
                {
                    continue;
                }
                if(ISC.isVisited(possibleMoves.get(i)))
                {
                    continue;
                }
                // there is a valid move
                thisState = possibleMoves.get(i);
                solutionPath.add(thisState);
                ISC.setVisit(thisState);
                visitedStates++;
                Moves = true;
                break;
            }
            if(!Moves) // there is not a valid move from the current State
            {
                solutionPath.remove(thisState);
                thisState = solutionPath.get(solutionPath.size() - 1);
            }
        }
        this.setNumberOfNodesEvaluated(visitedStates);
        ISC.resetProblem();
        return new Solution(solutionPath);
    }
}
