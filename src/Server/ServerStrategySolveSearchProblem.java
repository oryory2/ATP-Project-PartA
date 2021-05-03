package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchable;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy
{

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze newMaze = (Maze)fromClient.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            // Is the maze present in the file?
            // else
            BestFirstSearch best = new BestFirstSearch();
            SearchableMaze sMaze = new SearchableMaze(newMaze);
            Solution sol = best.solve(sMaze);
            toClient.writeObject(sol);

            fromClient.close();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
