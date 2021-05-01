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
        this.threadPool = Executors.newFixedThreadPool(10); // will change it in the next part

    }
    public void start()
    {
        threadPool.submit(() -> {StartReal(); });
        try
        {
            Thread.sleep(3000);

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
            ServerSocket serverSocket = new ServerSocket(this.port); // אתחול סרבר סוקט
            serverSocket.setSoTimeout(this.ListeningIntervalMS); // הקצבת זמן לקבלת בקשה לקישור מתוך לקוח
            System.out.println("Starting server at port:" + this.port);
            while(!stop) // הסרבר ימשיך לרוץ כל עוד..
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); // נוצר קשר עם לקוח
                    System.out.println("Client accepted : " + clientSocket.toString());
                    threadPool.submit(() -> {ServerStrategy(clientSocket); });
                }
                catch (SocketTimeoutException e)
                {
                    System.out.println("The time is out"); // במידה ואף לקוח לא יצר קשר תוך הזמן שהוקצב
                }
            }
            serverSocket.close();
            threadPool.shutdown();
        }
        catch (IOException e)
        {
            e.printStackTrace(); // במידה ויש בעיה עם פתיחת הסרבר סוקט (port בשימוש/לא תקין)
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

