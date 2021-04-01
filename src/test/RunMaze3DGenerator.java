package test;
import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.*;
public class RunMaze3DGenerator
{
    public static void main(String[] args)
    {
        testMaz3DeGenerator(new MyMaze3DGenerator());
    }
    public static void testMaz3DeGenerator(IMazeGenerator3D mazeGenerator)
    {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(500,500, 500)));
        Maze3D maze = mazeGenerator.generate(5,5,5);
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}