package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm
        /**
         * This Class describe a Searching Algorithm work by the DepthFirstSearch method
         */
{
    public DepthFirstSearch()
    {
        super();
        super.name = "DepthFirstSearch";
    }

    public Solution solve(ISearchable ISC)
    {
        AState startState = ISC.getStartState();
        AState thisState = startState;
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        ArrayList<AState> visitedStates = new ArrayList<AState>();
        solutionPath.add(startState);
        visitedStates.add(startState);
        boolean End = false;

        while(End == false)
        {
            if(thisState == ISC.getGoalState())
            {
                End = true;
                continue;
            }
            ArrayList<AState> possibleMoves = ISC.getAllPossibleStates(thisState);
            boolean Moves = false;

            for(int i = 0; i < possibleMoves.size(); i++)
            {
                if(!(possibleMoves.get(i).legalState()))
                {
                    continue;
                }
                else
                {
                    if(visitedStates.contains(possibleMoves.get(i)) == true)
                    {
                        continue;
                    }
                    else
                    {
                        thisState = possibleMoves.get(i);
                        solutionPath.add(thisState);
                        visitedStates.add(thisState);
                        Moves = true;
                        break;
                    }
                }
            }
            if(Moves == false)
            {
                solutionPath.remove(thisState);
                thisState = solutionPath.get(solutionPath.size() - 1);
            }
        }
        return new Solution(solutionPath);
    }
}
