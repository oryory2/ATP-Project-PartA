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
        this.in.read();
        return 0;
    }

    public int read(byte[] b) throws IOException
    {
        byte [] input = this.in.readAllBytes();
        for(int i = 0; i < 12; i++)
        {
            b[i] = input[i];
        }
        int flag = 1;
        int thisIndex = 12;
        for(int i = 12; i < input.length; i++)
        {
            if(flag == 0)
                flag = 1;
            else
                flag = 0;
            for(int j = 0; j < input[i]; j++)
            {
                b[thisIndex] = (byte)flag;
                thisIndex++;
            }
        }
        return 0;
    }
}