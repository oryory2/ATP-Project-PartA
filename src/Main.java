import algorithms.mazeGenerators.*;
import algorithms.search.*;
import test.RunMazeGenerator;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        EmptyMazeGenerator m = new EmptyMazeGenerator();
        Maze maze = m.generate(1000,1000);
        SearchableMaze Smaze = new SearchableMaze(maze);
        //DepthFirstSearch d = new DepthFirstSearch();
        //Solution s1 = d.solve(Smaze);
        BreadthFirstSearch b = new BreadthFirstSearch();
        Solution s2 = b.solve(Smaze);
        System.out.println("hey");

    }
}
