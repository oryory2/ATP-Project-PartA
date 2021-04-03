package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm /** This Abstract Class describe an Searching Algorithm */

{
    private String name;
    private int NumberOfNodesEvaluated;

     /**
     * constructor for abstract class ASearchingAlgorithm
     * initialize the class name
     * and the number of nodes evaluated to be minus one
     */
    public ASearchingAlgorithm()
    {
        this.name = "ASearchingAlgorithm";
        this.NumberOfNodesEvaluated = -1;
    }

     /**
     * @return the name of the algorithm (String)
     */
    public String getName()
    {
        return this.name;
    }

     /**
     * A method that allows to change the name of a certain searching algorithm
     * @param name the name wished to set to the algorithm as its name field
     */
    public void setName(String name)
    {
        if(name == null)
        {
            throw new RuntimeException("The name supplied is not legal (null)");
        }
        this.name = name;
    }

     /**
     * Returns how many nodes we went through during the run of the algorithm
     * The algorithm goes through a certain number of nodes
     * When a solution is reached its stops evaluating nodes
     * @return number of nodes evaluated (int)
     */
    public int getNumberOfNodesEvaluated()
    {
        return this.NumberOfNodesEvaluated;
    }

     /**
     * Sets the number of nodes evaluated
     * @param number the number we want to set the field to be
     */
    public void setNumberOfNodesEvaluated(int number)
    {
        this.NumberOfNodesEvaluated = number;
    }

     /**
     * Returns the solution path we found between two states
     * First, add "thisState" to the solution list
     * Then, Recursively add the prev state to the solution list
     * Until we reached the start state
     * @param startState The starting State of the problem we want to solve
     * @param thisState The Goal State of the problem we want to solve
     * @return Solution path (Solution)
     */
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
