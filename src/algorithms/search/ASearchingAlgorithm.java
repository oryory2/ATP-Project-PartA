package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private String name;
    private int num_of_nodes;
    private ISearchable Problem;

    public ASearchingAlgorithm(String name,ISearchable Problem){
        this.name=name;
        this.Problem = Problem;
        this.num_of_nodes = -1;
    }

    public String getName(){
        return this.name;
    }

    public int getNumberOfNodesEvaluated(){
        return this.num_of_nodes;
    }
}
