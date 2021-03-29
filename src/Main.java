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
        Maze maze = m.generate(10,10);
        SearchableMaze Smaze = new SearchableMaze(maze);
        DepthFirstSearch d = new DepthFirstSearch();
        Solution s = d.solve(Smaze);

    }
}
