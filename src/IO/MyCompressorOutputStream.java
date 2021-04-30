package IO;

import algorithms.mazeGenerators.Position;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private byte lastByte = 0;
    private int counter;

    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public void write(int b) throws IOException {
        if (b == this.lastByte)
            this.counter++;
        else {
            byte[] arr = new byte[counter];
            for (int i = 0; i < counter; i++) {
                arr[i] = lastByte;
            }
            this.out.write(arr);
            lastByte = ((byte) b);
            counter = 1;
        }
    }

    public void write(byte[] b) throws IOException
    {
        this.out.write(b);
    }
}