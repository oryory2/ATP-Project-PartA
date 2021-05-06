package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleDecompressorInputStream extends InputStream
{
    private InputStream in;


     /**
     * constructor
     * @param inputStream the inputStream field
     */
    public SimpleDecompressorInputStream(InputStream inputStream)
    {
        this.in = inputStream;
    }

     /**
     * A function that reads one byte from the inputStream (in)
     * @return 0 (int)
     */
    public int read() throws IOException
    {
        this.in.read(); // read a byte from the Stream
        return 0;
    }

     /**
     * Gets Compressed array(compressed by the simpleCompression algorithm)
     * And Decompresses it adapted to the format
     * @return 0 (int)
     */
    public int read(byte[] b) throws IOException
    {
        byte [] input = this.in.readAllBytes(); // read all the bytes from the Stream

        for(int i = 0; i < 12; i++) // read the first 12 bytes from the InPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
        {
            b[i] = input[i];
        }
        int flag = 1;
        int thisIndex = 12; // get the right inserting index to the b array

        for(int i = 12; i < input.length; i++) // inserting all the mazeArr values
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