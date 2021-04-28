import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.ArrayList;


public class Main
{
    public static void main(String[] args)
    {
        SimpleMazeGenerator e = new SimpleMazeGenerator();
        Maze m = e.generate(10,10);
        byte[] arr = m.toByteArray();
        System.out.println("dsf");



//[127 127 127 127 0 0 5 00]






    }
}
