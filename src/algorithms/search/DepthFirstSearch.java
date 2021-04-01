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
        int visitedStates = 0;
        solutionPath.add(startState);
        ISC.setVisit(startState);
        visitedStates++;
        boolean End = false;

        while(!End)
        {
            if(thisState.compStates(ISC.getGoalState()))
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

                if(!(possibleMoves.get(i).legalState())) // not legal indexes or it's a wall
                {
                    continue;
                }
                if(ISC.isVisited(possibleMoves.get(i)))
                {
                    continue;
                }
                thisState = possibleMoves.get(i);
                solutionPath.add(thisState);
                ISC.setVisit(thisState);
                visitedStates++;
                Moves = true;
                break;
            }
            if(!Moves)
            {
                solutionPath.remove(thisState);
                thisState = solutionPath.get(solutionPath.size() - 1);
            }
        }
        this.NumberOfNodesEvaluated = visitedStates;
        ISC.resetProblem();
        return new Solution(solutionPath);
    }
}
