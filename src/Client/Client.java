package Client;

import java.net.InetAddress;
import java.net.Socket;

public class Client
{

    private InetAddress ServerIP;
    private int ServerPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy)
    {
        this.ServerIP = serverIP;
        this.ServerPort = serverPort;
        this.strategy = strategy;
    }
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
