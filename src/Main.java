import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        MyMazeGenerator m = new MyMazeGenerator();
        Maze newMaze = m.generate(100, 100);
        newMaze.Print();
    }
}
