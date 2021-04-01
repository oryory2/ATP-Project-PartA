import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import test.RunMazeGenerator;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
/*        System.out.println("hey");
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = m.generate(1000,1000);
        SearchableMaze Smaze = new SearchableMaze(maze);
        DepthFirstSearch d = new DepthFirstSearch();
        Solution s1 = d.solve(Smaze);
        BreadthFirstSearch b = new BreadthFirstSearch();
        Solution s2 = b.solve(Smaze);
        System.out.println("hey");
        BestFirstSearch BF = new BestFirstSearch();
        Solution s3 = BF.solve(Smaze);
        System.out.println("check");*/
        IMazeGenerator3D g = new MyMaze3DGenerator();
        Maze3D newMaze = g.generate(2,100,100);
        System.out.println("gh");
    }
}
