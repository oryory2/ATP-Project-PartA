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
        this.out.write(b); // write the byte b to the Stream
    }

    public void write(byte[] b) throws IOException
    {
        for(int i = 0; i < 12; i++) // write the first 12 bytes to the OutPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
        {
            this.out.write(b[i]);
        }
        for(int i = 12 ; i < b.length; i++) // write the rest bytes - the mazeArray values
        {
            int thisIndex = i - 12; // get the right inserting index to the b array
            if(b[i] == 1)
            {
                if(thisIndex <= 127) // if the number is lower than 127 than write it to the Stream
                    this.out.write(thisIndex);
                else
                {
                    int temp = thisIndex;
                    while(temp > 127) // insert a number that bigger than 127 by 127 - 0 - 127 - 0 -......
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