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

    private Map<String, String> hashMap;
    private AtomicInteger counter;


    public ServerStrategySolveSearchProblem()
    {
        this.counter = new AtomicInteger();
        this.hashMap = new HashMap<String,String>();

        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        System.out.println(System.getProperty("java.io.tmpdir")); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!למחוק בסוף!!!!!!!!!!!!

        File folder = new File(tempDirectoryPath);
        File[] listOfFiles = folder.listFiles();
        int maxMazeCounter = -1;

        for(int i = 0; i < listOfFiles.length; i++)
        {
            if(listOfFiles[i].getName().contains("maze")) // checks for Mazes sent in the past
            {
                String mazeFileName = tempDirectoryPath + listOfFiles[i].getName();
                String solFileName = mazeFileName;
                solFileName = solFileName.replace("maze", "sol");

                this.hashMap.put(mazeFileName,solFileName); // add the Maze with his Solution to the hashMap

                String fileNumber = mazeFileName;
                fileNumber = fileNumber.replace(tempDirectoryPath + "maze", "");

                if(maxMazeCounter < Integer.parseInt(fileNumber))
                {
                    maxMazeCounter = Integer.parseInt(fileNumber);
                }
            }
        }
        this.counter.set(maxMazeCounter + 1); // Set the right next counter
    }

    public Map<String, String> getHashMap()
    {
        return this.hashMap;
    }

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Map<String, String> hashMap = this.getHashMap();

            Maze newMaze = (Maze)fromClient.readObject();
            for (Map.Entry elem : hashMap.entrySet())
            {
                if(compareMazeFromFile(newMaze, (String) elem.getKey()))
                {
                    // get the solution that saved in the Value of this key and return it

                    String solFileName = (String)(elem.getValue());
                    FileInputStream fromFile = new FileInputStream(solFileName);
                    ObjectInputStream solFromFile = new ObjectInputStream(fromFile);
                    Solution sol =(Solution)(solFromFile.readObject());

                    toClient.writeObject(sol);
                    toClient.flush();
                    fromClient.close();
                    toClient.close();
                    return;
                }
            }

            BestFirstSearch best = new BestFirstSearch();
            SearchableMaze sMaze = new SearchableMaze(newMaze);
            Solution sol = best.solve(sMaze);

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String numOfSolution = String.valueOf(this.counter.getAndIncrement());

            String mazeFileName = tempDirectoryPath + "maze" + numOfSolution;
            FileOutputStream toFile1 = new FileOutputStream(mazeFileName);
            ObjectOutputStream mazeToFile = new ObjectOutputStream(toFile1);
            mazeToFile.writeObject(newMaze); // send the Maze into the new file

            String solFileName = tempDirectoryPath + "sol" + numOfSolution;
            FileOutputStream toFile2 = new FileOutputStream(solFileName);
            ObjectOutputStream solToFile = new ObjectOutputStream(toFile2);
            solToFile.writeObject(sol); // send the Solution into the new file

            hashMap.put(mazeFileName, solFileName); // insert the (MazeFileName, SolutionFileName) to the hashMap

            toClient.writeObject(sol); // return the solution to the Client
            toClient.flush();
            fromClient.close();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static boolean compareMazeFromFile(Maze thisMaze, String otherMazeFileName)
    {
        try {
            FileInputStream fromFile = new FileInputStream(otherMazeFileName);
            ObjectInputStream mazeFromFile = new ObjectInputStream(fromFile);
            Maze otherMaze = (Maze)(mazeFromFile.readObject());
            return thisMaze.isEqual(otherMaze);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
