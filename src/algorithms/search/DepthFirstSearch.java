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
            if(thisState.compStates(ISC.getGoalState()))
            {
                End = true;
                continue;
            }
            ArrayList<AState> possibleMoves = ISC.getAllPossibleStates(thisState);
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
                else
                {
                    if(ASearchingAlgorithm.isVisited(visitedStates, possibleMoves.get(i)) == true)
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
        this.NumberOfNodesEvaluated = visitedStates.size();
        return new Solution(solutionPath);
    }


}
