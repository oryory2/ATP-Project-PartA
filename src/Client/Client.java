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
            Socket serverSocket = new Socket(this.ServerIP, this.ServerPort); // שולח לשרת בקשת התחברות
            System.out.println("Client is now connected to the Server: IP: " + this.ServerIP);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace(); // הסרבר לא קיים/לא קיבל את הבקשה
        }
    }
}
