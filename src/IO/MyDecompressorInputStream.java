package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream
{
    private InputStream in;

    public MyDecompressorInputStream(InputStream inputStream)
    {
        this.in = inputStream;
    }

    public int read() throws IOException
    {
        this.in.read(); // read a byte from the Stream
        return 0;
    }

    public int read(byte[] b) throws IOException
    {
        byte [] input = this.in.readAllBytes(); // read all the bytes from the Stream

        for(int i = 0; i < 12; i++) // read the first 12 bytes from the InPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
        {
            b[i] = input[i];
        }

        int insertIndex = 12; // get the right inserting index to the b array
        int thisIndex = 0;
        ArrayList<Integer> fixedIndexes = fixedIndexes(input);

        for(int i = 0; i < fixedIndexes.size(); i++)
        {
            while(thisIndex < fixedIndexes.get(i)) // while the index of the next '1' val in the array is bigger than thisIndex, keep insert '0'
            {
                b[insertIndex] = 0;
                insertIndex++;
                thisIndex++;
            }
            b[insertIndex] = 1; // insert '1'
            insertIndex++;
            thisIndex++;
        }
        for(int i = insertIndex; i < b.length; i++) // insert '0' until the end of the b array
        {
            b[i] = 0;
        }
        return 0;
    }

    public static ArrayList<Integer> fixedIndexes(byte[] arr)
    {
        int sum = 0;
        ArrayList<Integer> ListArr = new ArrayList<Integer>();

        for(int i = 12; i < arr.length; i++) // start working from index 12 (the start of the mazeArr values)
        {
            sum += arr[i];
            if((arr[i] != 127) && (arr[i] != 0))
            {
                ListArr.add(sum);
                sum = 0;
            }
            else if((arr[i] == 127) && (i < arr.length - 1) && (arr[i + 1] != 0)) // check if we need add more to sum (left indexSize is more than 127)
            {
                ListArr.add(sum);
                sum = 0;
            }
            else if((i == 12) && (arr[i] == 0)) // check if the first index that '1' is shown is 0
            {
                ListArr.add(0);
            }
        }
        return ListArr;
    }

}
