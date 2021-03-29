import algorithms.mazeGenerators.*;
import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.SearchableMaze;
import test.RunMazeGenerator;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        MyMazeGenerator m = new MyMazeGenerator();
        //System.out.println(m.measureAlgorithmTimeMillis(1000, 1000));
        //RunMazeGenerator.testMazeGenerator(new MyMazeGenerator());
        Maze maze = m.generate(3,3);
        SearchableMaze Smaze = new SearchableMaze(maze);
        Position p = new Position(1,1);
        AState tstate = new MazeState(p);
        ArrayList<AState> gg = Smaze.getAllPossibleStates(tstate);


    }
}
