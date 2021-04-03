package algorithms.search;

public interface ISearchingAlgorithm /** This Interface describe an Searching Algorithm */

{
    Solution solve(ISearchable ISC);
    String getName();
    int getNumberOfNodesEvaluated();
}
