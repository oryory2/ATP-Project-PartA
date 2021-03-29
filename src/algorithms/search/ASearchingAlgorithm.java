package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm
        /**
         * This Abstract Class describe an Searching Algorithm
         */
{

    protected String name;
    protected int NumberOfNodesEvaluated;

    public ASearchingAlgorithm()
    {
        this.name = "ASearchingAlgorithm";
        this.NumberOfNodesEvaluated = -1;
    }

    public String getName()
    {
        return this.name;
    }

    public int getNumberOfNodesEvaluated()
    {
        return this.NumberOfNodesEvaluated;
    }

    public static boolean isVisited(ArrayList<AState> States, AState thisState)
    {
        for(int i = 0; i < States.size(); i++)
        {
            if(thisState.compStates(States.get(i)))
                return true;
        }
        return false;
    }

    public static Solution restoreSolutionPath(AState startState, AState thisState)
    {
        ArrayList<AState> SolutionPath = new ArrayList<AState>();
        boolean flag = false;

        while(!flag)
        {
            SolutionPath.add(0, thisState);
            if(thisState.compStates(startState))
            {
                flag = true;
                continue;
            }
            thisState = thisState.getPrevState();
        }
        return new Solution(SolutionPath);
    }
}
