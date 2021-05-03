package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream
{
    public OutputStream out;
    private byte lastByte = 0;
    private int counter;
    private boolean flag = false;


    public SimpleCompressorOutputStream(OutputStream outputStream)
    {
        this.out = outputStream;
    }

    public void write(int b) throws IOException
    {
        if ((b == lastByte) && (!flag))
        {
            counter++;
            if (counter == 128) {
                this.out.write(127);
                this.out.write(0);
                counter = 1;
            }
        }
        else if ((b == lastByte) && (flag))
        {
            counter++;
            if(counter == 128)
            {
                this.out.write(127);
                this.out.write(0);
                counter = 1;
            }
        }
        else if((b != lastByte) && (flag))
        {
            this.out.write(counter);
            counter = 1;
            flag = false;
            lastByte = (byte)b;
        }
        else if((b != lastByte) && (!flag))
        {
            this.out.write(counter);
            counter = 1;
            flag = true;
            lastByte = (byte)b;
        }
    }
    public void write(byte[] b) throws IOException
    {
        for(int i = 0; i < 12; i++)
        {
            this.out.write(b[i]);
        }

        for(int i = 12 ; i < b.length; i++)
        {
            write(b[i]);
        }
        if(counter != 0)
            this.out.write(counter);
    }
}



