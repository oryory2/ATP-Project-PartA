package IO;

import algorithms.mazeGenerators.Position;
import jdk.jfr.Unsigned;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream
{

    public OutputStream out;

     /**
     * constructor
     * @param outputStream the outputStream field
     */
    public MyCompressorOutputStream(OutputStream outputStream)
    {
        this.out = outputStream;
    }


     /**
     * A function that write one byte to the outputStream (out)
     */
    public void write(int b) throws IOException
    {
        this.out.write(b); // write the byte b to the Stream
    }

     /**
     * A function that used to write the compressed representation of the Maze (byte Array) to the outPutStream
     * in this algorithm we are inserting to the array (from index 12 until the end) all the indexes that '1' was shown in them
     * in this method we can understand weather we have '1' or '0' for every index in the real mazeArr
     * @param b The number we send to the outputStream
     */
    public void write(byte[] b) throws IOException
    {
        for(int i = 0; i < 12; i++) // write the first 12 bytes to the OutPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
        {
            this.out.write(b[i]);
        }
        int counter = 0;
        String s = "";
        int lastIter = 7;
        for(int i = 12; i < b.length; i++)
        {
            s += b[i];
            counter++;
            if((counter == 7) || (i == b.length - 1))
            {
                if(i == b.length - 1)
                {
                    lastIter = counter;
                }
                byte binaryByte = (byte)(int)Integer.valueOf(s, 2);
                this.out.write(binaryByte);
                counter = 0;
                s = "";
            }
        }
        this.out.write(lastIter);
    }
}