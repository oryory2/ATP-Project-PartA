package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDecompressorInputStream extends InputStream
{

    private InputStream in;
    public MyDecompressorInputStream(InputStream inputStream)
    {
        this.in = inputStream;
    }


    public int read() throws IOException {
        return 0;
    }
}
