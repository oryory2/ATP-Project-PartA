import algorithms.mazeGenerators.*;
import algorithms.search.*;
import test.RunMazeGenerator;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = m.generate(5,5);
        SearchableMaze Smaze = new SearchableMaze(maze);
        DepthFirstSearch d = new DepthFirstSearch();
        Solution s1 = d.solve(Smaze);
//        BreadthFirstSearch b = new BreadthFirstSearch();
//        Solution s2 = b.solve(Smaze);
        System.out.println("hey");
        BestFirstSearch BF = new BestFirstSearch();
        Solution s3 = BF.solve(Smaze);
        System.out.println("check");
    }
}
