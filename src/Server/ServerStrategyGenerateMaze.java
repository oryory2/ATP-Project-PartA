package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy
{

     /**
     * A function that receives a request from a client
     * and generates a maze for him according to the Parameters (row,column) the client requested
     * @param inFromClient the Server's Input stream
     * @param outToClient the Server's Output stream
     */
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient); // fromClient
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient); // toClient

            OutputStream out = new ByteArrayOutputStream(); // use this Stream to enable sending the byteArray to the Client
            MyCompressorOutputStream middleStream = new MyCompressorOutputStream(out); // Compressor

            Configurations c = Configurations.getInstance(); // load the Properties of the Program
            Object[] configurations = c.LoadProp();

            int[] arr = ((int[]) fromClient.readObject());
            Maze newMaze = ((IMazeGenerator)(configurations[1])).generate(arr[0], arr[1]); // Generating newMaze by the Client request

            middleStream.write(newMaze.toByteArray());
            toClient.writeObject(((ByteArrayOutputStream)middleStream.out).toByteArray()); // sending the newMaze to the Client
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
