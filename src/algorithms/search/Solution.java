package algorithms.search;
import java.util.ArrayList;

public class Solution /** This Class describe a Solution of an SearchAble problem */

{
    private ArrayList<AState> AstateArr;

    public Solution(ArrayList<AState> AstateArr)
    {
        if(AstateArr == null)
        {
            throw new RuntimeException("The ArrayList that supplied is not legal! (null)");
        }
        this.AstateArr = AstateArr;
    }
    public ArrayList<AState> getSolutionPath()
    {
        return this.AstateArr;
    }
}
