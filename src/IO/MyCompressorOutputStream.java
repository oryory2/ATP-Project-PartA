package IO;

import algorithms.mazeGenerators.Position;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream
{

    public OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream)
    {
        this.out = outputStream;
    }

    public void write(int b) throws IOException
    {
        this.out.write(b);
    }

    public void write(byte[] b) throws IOException
    {
        for(int i = 0; i < 12; i++)
        {
            this.out.write(b[i]);
        }
        for(int i = 12 ; i < b.length; i++)
        {
            int thisIndex = i - 12;
            if(b[i] == 1)
            {
                if(thisIndex <= 127)
                    this.out.write(thisIndex);
                else
                {
                    int temp = thisIndex;
                    while(temp > 127)
                    {
                        this.out.write(127);
                        this.out.write(0);
                        temp -= 127;
                    }
                    if(temp != 0)
                        this.out.write(temp);
                }
            }

        }
    }
}