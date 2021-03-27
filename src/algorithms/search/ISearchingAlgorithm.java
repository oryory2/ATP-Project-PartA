package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable ISC);
    String getName();
    int getNumberOfNodesEvaluated();


}
