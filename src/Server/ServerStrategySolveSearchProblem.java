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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy
{

    private Map<Maze, String> hashMap;
    private static AtomicInteger counter;


    public ServerStrategySolveSearchProblem()
    {
        ServerStrategySolveSearchProblem.counter = new AtomicInteger();
        this.hashMap = new HashMap<Maze,String>();
    }

    public Map<Maze, String> getHashMap()
    {
        return this.hashMap;
    }

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Map<Maze, String> hashMap = this.getHashMap();
            Maze newMaze = (Maze)fromClient.readObject();
            for (Map.Entry elem : hashMap.entrySet())
            {
                if(newMaze.isEqual((Maze)elem.getKey()) == true)
                {
                    // get the solution that saved in the Value of this key and return it

                    String solFileName = (String)(elem.getValue());
                    FileInputStream fromFile = new FileInputStream(solFileName);
                    ObjectInputStream solFromFile = new ObjectInputStream(fromFile);
                    Solution sol =(Solution)(solFromFile.readObject());

                    toClient.writeObject(sol);
                    fromClient.close();
                    toClient.close();
                    return;
                }
            }

            BestFirstSearch best = new BestFirstSearch();
            SearchableMaze sMaze = new SearchableMaze(newMaze);
            Solution sol = best.solve(sMaze);

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String numOfSolution = String.valueOf(ServerStrategySolveSearchProblem.counter.getAndIncrement());
            String solFileName = tempDirectoryPath + numOfSolution;
            FileOutputStream toFile = new FileOutputStream(solFileName);
            ObjectOutputStream solToFile = new ObjectOutputStream(toFile);

            solToFile.writeObject(sol); // send the solution into the new file
            hashMap.put(newMaze, solFileName); // insert the (Maze, name of the file) to the HashMap

            toClient.writeObject(sol); // return the solution to the Client
            fromClient.close();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
