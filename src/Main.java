import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
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
        Maze maze = m.generate(100,100);
        //SearchableMaze Smaze = new SearchableMaze(maze);
        //DepthFirstSearch d = new DepthFirstSearch();
        //Solution s1 = d.solve(Smaze);
        //BreadthFirstSearch b = new BreadthFirstSearch();
        //Solution s2 = b.solve(Smaze);
        //BestFirstSearch BF = new BestFirstSearch();
        //Solution s3 = BF.solve(Smaze);
        //IMaze3DGenerator g = new MyMaze3DGenerator();
        //Maze3D newMaze = g.generate(2,500, 500);
        //SearchableMaze3D sMaze = new SearchableMaze3D(newMaze);
        //BreadthFirstSearch bfs = new BreadthFirstSearch();
        //Solution s1 = bfs.solve(sMaze);
        //BestFirstSearch bfs2 = new BestFirstSearch();
        //Solution s2 = bfs2.solve(sMaze);
        //DepthFirstSearch d = new DepthFirstSearch();
        //Solution s3 = d.solve(sMaze);
        maze.print();
        //newMaze.print();
        System.out.println("ghgh");
        System.out.println("gh");
    }
}
