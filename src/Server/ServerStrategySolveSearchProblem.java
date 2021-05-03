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

            //FileInputStream fileIn = new FileInputStream(tempDirectoryPath);
            //ObjectInputStream fromFile = new ObjectInputStream(fileIn);

//            File folder = new File(tempDirectoryPath);
//            for(File fileEntry : folder.listFiles())
//            {
//                FileInputStream fileIn1 = new FileInputStream(fileEntry.getAbsolutePath());
//                ObjectInputStream fromFile1 = new ObjectInputStream(fileIn1);
//                Maze temp = (Maze)fromFile1.readObject();
//                if(temp.equal(newMaze))
//                {
//                    // go to solution of same maze and return
//                }
//            }
//            //else - the maze is a new maze
//
//            FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath);
//            ObjectOutputStream toFile = new ObjectOutputStream(fileOut);
//            toFile.writeObject(newMaze);
//            toFile.close();

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
