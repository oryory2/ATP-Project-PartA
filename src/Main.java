import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hey");
        EmptyMazeGenerator empt = new EmptyMazeGenerator();
        Maze maze = empt.generate(20, 8);
        SimpleMazeGenerator d = new SimpleMazeGenerator();
        Maze c = d.generate(10, 30);
        c.Print();
        maze.Print();

    }
}
