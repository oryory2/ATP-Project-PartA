package algorithms.search;
import java.util.ArrayList;

public class Solution /** This Class describe a Solution of an SearchAble problem */

{
    private ArrayList<AState> AstateArr;

     /**
     * Constructor gets an ArrayList of AState
     * presenting the solution path
     * and sets it as the AstateArr field
     * @param AstateArr the solution path array
     */
    public Solution(ArrayList<AState> AstateArr)
    {
        if(AstateArr == null)
        {
            throw new RuntimeException("The ArrayList that supplied is not legal! (null)");
        }
        this.AstateArr = AstateArr;
    }

     /**
     * @return the Solution Path of a problem
     */
    public ArrayList<AState> getSolutionPath()
    {
        return this.AstateArr;
    }
}
