package Server;
import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy
{
    void applyStrategy(InputStream inFromClient, OutputStream outToClient); // What we get from the client, and what we send to the client
}
