import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.ArrayList;
import java.util.HashMap;


public class Main
{
    public static void main(String[] args)
    {


        MyMazeGenerator e = new MyMazeGenerator();
        Maze m1 = e.generate(10,10);
        Maze m3 = e.generate(10,10);
        Maze m2 = e.generate(10,10);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(m1.getHashStr(), "abc");

        if(hashMap.containsKey(m1.getHashStr()))
        {
            System.out.println("work");
        }
        if(hashMap.containsKey(m2.getHashStr()))
        {
            System.out.println("not work");
        }

    }
}
