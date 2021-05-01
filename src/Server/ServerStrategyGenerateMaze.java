package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy
{

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            OutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream middleStream = new MyCompressorOutputStream(out);

            int[] arr = ((int[]) fromClient.readObject());
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze newMaze = mazeGenerator.generate(arr[0], arr[1]);
            middleStream.write(newMaze.toByteArray());
            toClient.writeObject(((ByteArrayOutputStream)middleStream.out).toByteArray());
            toClient.flush();

            fromClient.close();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
