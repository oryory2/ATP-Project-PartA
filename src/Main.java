import IO.MyDecompressorInputStream;
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

        byte[]arrr = new byte[10];
        arrr[0] = 0;
        arrr[1] = 5;
        arrr[2] = 127;
        arrr[3] = 8;
        arrr[4] = 127;
        arrr[5] = 127;
        arrr[6] = 127;
        arrr[7] = 3;
        arrr[8] = 9;
        arrr[9] = 10;
        ArrayList<Integer> f = MyDecompressorInputStream.fixedIndexes(arrr);

    }
}
