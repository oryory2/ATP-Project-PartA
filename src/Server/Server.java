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

     /**
     * constructor
     * @param port The port from which the server will wait for clients
     * @param ListeningIntervalMS the time the server will wait until it checks for new Client
     * @param strategy the strategy of the client
     */
    public Server(int port, int ListeningIntervalMS, IServerStrategy strategy)
    {
        Configurations c = Configurations.getInstance();
        Object[] configurations = c.LoadProp();
        this.port = port; // the port number of the server
        this.ListeningIntervalMS = ListeningIntervalMS;
        this.strategy = strategy; // the strategy the server will use
        this.threadPool = Executors.newFixedThreadPool((int)(configurations[0])); // the number of Client we can serve in concurrent
    }

     /**
     * run the Server
     */
    public void start()
    {
        threadPool.submit(() -> {StartReal(); }); // run the server

        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

     /**
     * run the Server
     * open a new ServerSocket, wait for Clients to connect to it, and apply it's strategies on them
     */
    public void StartReal()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(this.port); // Server Socket initialization
            serverSocket.setSoTimeout(this.ListeningIntervalMS); // Allocation of time for requesting a link from a Client
            //System.out.println("Starting server at port:" + this.port);
            while(!stop) // The server will continue to run as long as ..
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); // Contacting the Client
                    //System.out.println("Client accepted : " + clientSocket.toString());
                    threadPool.submit(() -> {ServerStrategy(clientSocket); });
                }
                catch (SocketTimeoutException e)
                {
                    //System.out.println("The time is out"); // If no Client contacted within the allotted time
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

     /**
     * apply the ServerStrategy on the connected Client
     * @param clientSocket the Socket of the connected Client
     */
    private void ServerStrategy(Socket clientSocket)
    {
        try
        {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream()); // apply the strategy with the Client
            clientSocket.close(); // disconnect the client from the server
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

     /**
     * Stop the Server
     */
    public void stop()
    {
        this.stop = true; // Stop the server
    }
}

