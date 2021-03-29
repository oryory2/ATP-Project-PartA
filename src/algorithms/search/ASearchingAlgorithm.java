package algorithms.search;

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
}
