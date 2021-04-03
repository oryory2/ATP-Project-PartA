package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm /** This Abstract Class describe an Searching Algorithm */

{

    private String name;
    private int NumberOfNodesEvaluated;


    public ASearchingAlgorithm()
    {
        this.name = "ASearchingAlgorithm";
        this.NumberOfNodesEvaluated = -1;
    }

    /**
     * Returns the name of a certain searching algorithm
     * @return the algorithm's name
     */
    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        if(name == null)
        {
            throw new RuntimeException("The name supplied is not legal (null)");
        }
        this.name = name;
    }

    public int getNumberOfNodesEvaluated()
    {
        return this.NumberOfNodesEvaluated;
    }

    public void setNumberOfNodesEvaluated(int number)
    {
        this.NumberOfNodesEvaluated = number;
    }


    public static Solution restoreSolutionPath(AState startState, AState thisState)
    {
        if((startState == null) || (thisState == null))
        {
            throw new RuntimeException("One or more of the supplied arguments is not legal (null)");
        }
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
