package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

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
     * 2. Hashmap = the hashMap <Maze.hashStr(), solFileName>
     * when we start the server, it's check for mazes/solutions he created in the past, and add them to the hashMap
     */
    public ServerStrategySolveSearchProblem()
    {
        this.counter = new AtomicInteger(); // count the number of maze/sol files
        this.hashMap = new HashMap<String,String>(); // haseMap <Maze, solFileName>

        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        System.out.println(System.getProperty("java.io.tmpdir"));

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

                Maze thisMaze = getMazeFromFile(mazeFileName); // getting the maze saved in the file "mazeFileName"

                this.hashMap.put(thisMaze.getHashStr(),solFileName); // add the Maze with his Solution to the hashMap

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
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Map<String, String> hashMap = this.getHashMap();

            Maze newMaze = (Maze)fromClient.readObject();
            String hashcode = newMaze.getHashStr();
            if(hashMap.containsKey(hashcode))

                {
                    // get the solution that saved in the Value of this key and return it

                    String solFileName = hashMap.get(newMaze.getHashStr());
                    FileInputStream fromFile = new FileInputStream(solFileName);
                    ObjectInputStream solFromFile = new ObjectInputStream(fromFile);
                    Solution sol = (Solution)(solFromFile.readObject()); // get the sol save in File "solFileName"

                    toClient.writeObject(sol); // return the solution to the Client
                    toClient.flush();
                    fromClient.close();
                    toClient.close();
                    return;
                }


            Configurations c = Configurations.getInstance(); // load the Properties of the Program
            Object[] configurations = c.LoadProp();

            SearchableMaze sMaze = new SearchableMaze(newMaze);
            Solution sol = ((ASearchingAlgorithm)(configurations[2])).solve(sMaze);

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

            hashMap.put(newMaze.getHashStr(), solFileName); // insert the (MazeFileName, SolutionFileName) to the hashMap

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
     * A function that receives a File, and gets the Maze inside it
     * @param MazeFileName the Filename of the File with the maze inside
     * @return The maze inside the file (Maze)
     */
    private static Maze getMazeFromFile(String MazeFileName)
    {
        try
        {
            FileInputStream fromFile = new FileInputStream(MazeFileName);
            ObjectInputStream toObject = new ObjectInputStream(fromFile);
            Maze newMaze = (Maze)(toObject.readObject());
            return newMaze;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
