import IO.MyDecompressorInputStream;
import Server.Configurations;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import test.RunCommunicateWithServers;

import java.util.ArrayList;
import java.util.HashMap;


public class Main
{
    public static void main(String[] args)
    {
//        RunCommunicateWithServers.gg();
//        int i = 0;
//        while( i == 0)
//        {
//
//        }
        Configurations c = Configurations.getInstance();
        System.out.println("h");
        Object[] e = c.LoadProp();
    }
}
