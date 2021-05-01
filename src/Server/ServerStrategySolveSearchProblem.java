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
            InputStream in = new MyDecompressorInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            byte[] b = in.readAllBytes();
            Maze thisMaze = new Maze(b);

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            // המייז קיים בקובץ?
            // אחרת
            BestFirstSearch best = new BestFirstSearch();
            SearchableMaze sMaze = new SearchableMaze(thisMaze);
            Solution sol = best.solve(sMaze);
            toClient.writeObject(sol);

            in.close();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
