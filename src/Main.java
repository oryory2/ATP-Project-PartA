import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.ArrayList;


public class Main
{
    public static void main(String[] args)
    {
        SimpleMazeGenerator e = new SimpleMazeGenerator();
        Maze m1 = e.generate(5,5);
        byte[] arr = m1.toByteArray();
        Maze m2 = new Maze(arr);
        System.out.println("dsf");
        byte a;

    }
}
