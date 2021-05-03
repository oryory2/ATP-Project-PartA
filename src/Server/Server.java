package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    private int port;
    private int ListeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop = false;
    private ExecutorService threadPool;

    public Server(int port, int ListeningIntervalMS, IServerStrategy strategy)
    {
        this.port = port;
        this.ListeningIntervalMS = ListeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(10); // we will change it in the next part

    }
    public void start()
    {
        threadPool.submit(() -> {StartReal(); });
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public void StartReal()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(this.port); // Server Socket initialization
            serverSocket.setSoTimeout(this.ListeningIntervalMS); // Allocation of time for requesting a link from a Client
            System.out.println("Starting server at port:" + this.port);
            while(!stop) // The server will continue to run as long as ..
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); // Contacting the Client
                    System.out.println("Client accepted : " + clientSocket.toString());
                    threadPool.submit(() -> {ServerStrategy(clientSocket); });
                }
                catch (SocketTimeoutException e)
                {
                    System.out.println("The time is out"); // If no Client contacted within the allotted time
                }
            }
            serverSocket.close();
            threadPool.shutdown();
        }
        catch (IOException e)
        {
            e.printStackTrace(); // If there is a problem with opening a server socket (port used / invalid)
        }
    }

    private void ServerStrategy(Socket clientSocket)
    {
        try
        {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void stop()
    {
        this.stop = true;
    }
}

