package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleDecompressorInputStream extends InputStream
{
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream)
    {
        this.in = inputStream;
    }


    public int read() throws IOException
    {
        return 0;
    }

    public int read(byte[] b) throws IOException
    {
        this.in.read(b);
        return 0;
    }
}