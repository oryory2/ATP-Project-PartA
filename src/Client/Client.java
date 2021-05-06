package Client;

import java.net.InetAddress;
import java.net.Socket;

public class Client
{

    private InetAddress ServerIP;
    private int ServerPort;
    private IClientStrategy strategy;

     /**
     * constructor
     * @param serverIP the IP Address of the Server
     * @param serverPort The port the server is waiting for a client from
     * @param strategy the strategy of the client
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy)
    {
        this.ServerIP = serverIP;
        this.ServerPort = serverPort;
        this.strategy = strategy;
    }

     /**
     * A function used to connect with the server and apply the Client strategy
     * Catches the exception in case the connection fails
     */
    public void communicateWithServer()
    {
        try
        {
            Socket serverSocket = new Socket(this.ServerIP, this.ServerPort); // sending the server connection request
            System.out.println("Client is now connected to the Server: IP: " + this.ServerIP);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace(); // the server is not available/not on
        }
    }
}
