import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import test.RunMazeGenerator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        MyMazeGenerator m = new MyMazeGenerator();
        System.out.println(m.measureAlgorithmTimeMillis(1000, 1000));
        RunMazeGenerator.testMazeGenerator(new MyMazeGenerator());
    }
}
