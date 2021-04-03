package algorithms.search;

public interface ISearchingAlgorithm /** This Interface describe an Searching Algorithm */

{
    Solution solve(ISearchable ISC);
    String getName();
    void setName(String name);
    int getNumberOfNodesEvaluated();
    void setNumberOfNodesEvaluated(int number);
}
