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


     /**
     * constructor
     * initialize two fields:
     * 1. counter = Atomic integer that provide us the number of the mazeFiles created in the past
     * 2. Hashmap = the hashMap <mazeFileName, solFileName>
     * when we start the server, it's check for mazes/solutions he created in the past, and add them to the hashMap
     */
    public ServerStrategySolveSearchProblem()
    {
        this.counter = new AtomicInteger(); // count the number of maze/sol files
        this.hashMap = new HashMap<String,String>(); // haseMap <mazeFileName, solFileName>

        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        System.out.println(System.getProperty("java.io.tmpdir")); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!למחוק בסוף!!!!!!!!!!!!

        File folder = new File(tempDirectoryPath);
        File[] listOfFiles = folder.listFiles();
        int maxMazeCounter = -1;

        for(int i = 0; i < listOfFiles.length; i++) // adding all the maze/sol files from the past to the hashMap
        {
            if(listOfFiles[i].getName().contains("maze")) // checks for Mazes sent in the past
            {
                String mazeFileName = tempDirectoryPath + listOfFiles[i].getName();
                String solFileName = mazeFileName;
                solFileName = solFileName.replace("maze", "sol");

                this.hashMap.put(mazeFileName,solFileName); // add the Maze with his Solution to the hashMap

                String fileNumber = mazeFileName;
                fileNumber = fileNumber.replace(tempDirectoryPath + "maze", "");

                if(maxMazeCounter < Integer.parseInt(fileNumber)) // getting the current max maze/sol File number
                {
                    maxMazeCounter = Integer.parseInt(fileNumber);
                }
            }
        }
        this.counter.set(maxMazeCounter + 1); // Set the right next counter
    }

     /**
     * @return the hashMap of the server (Map<String, String>)
     */
    public Map<String, String> getHashMap()
    {
        return this.hashMap;
    }

     /**
     * A function that receives a maze from the client and return to him the solution of the maze
     * @param inFromClient the Server's Input stream
     * @param outToClient the Server's Output stream
     */
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
                    Solution sol = (Solution)(solFromFile.readObject()); // get the sol save in File "solFileName"

                    toClient.writeObject(sol); // return the solution to the Client
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

            String solFileName = tempDirectoryPath + "sol" + numOfSolution;
            FileOutputStream toFile2 = new FileOutputStream(solFileName);
            ObjectOutputStream solToFile = new ObjectOutputStream(toFile2);
            solToFile.writeObject(sol); // send the Solution into the new file

            String mazeFileName = tempDirectoryPath + "maze" + numOfSolution;
            FileOutputStream toFile1 = new FileOutputStream(mazeFileName);
            ObjectOutputStream mazeToFile = new ObjectOutputStream(toFile1);
            mazeToFile.writeObject(newMaze); // send the Maze into the new file

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

     /**
     * A function that receives a maze and a mazeFile, and compare both mazes
     * @param thisMaze the maze from the Client
     * @param otherMazeFileName the name of the mazeFile of other maze
     * @return true/false (boolean)
     */
    private static boolean compareMazeFromFile(Maze thisMaze, String otherMazeFileName)
    {
        try {
            FileInputStream fromFile = new FileInputStream(otherMazeFileName);
            ObjectInputStream mazeFromFile = new ObjectInputStream(fromFile);
            Maze otherMaze = (Maze)(mazeFromFile.readObject()); // get the otherMaze from the File "otherMazeFileName"
            return thisMaze.isEqual(otherMaze);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
