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
        return 0;
    }

    public int read(byte[] b) throws IOException
    {
        byte [] input = this.in.readAllBytes();
        for(int i = 0; i < 12; i++)
        {
            b[i] = input[i];
        }

        int insertIndex = 12;
        int thisIndex = 0;
        ArrayList<Integer> fixedIndexes = fixedIndexes(input);

        for(int i = 0; i < fixedIndexes.size(); i++)
        {
            while(thisIndex < fixedIndexes.get(i))
            {
                b[insertIndex] = 0;
                insertIndex++;
                thisIndex++;
            }
            b[insertIndex] = 1;
            insertIndex++;
            thisIndex++;
        }
        for(int i = insertIndex; i < b.length; i++)
        {
            b[i] = 0;
        }
        return 0;
    }

    public static ArrayList<Integer> fixedIndexes(byte[] arr)
    {
        int sum = 0;
        ArrayList<Integer> ListArr = new ArrayList<Integer>();
        for(int i = 12; i < arr.length; i++)
        {
            sum += arr[i];
            if((arr[i] != 127) && (arr[i] != 0))
            {
                ListArr.add(sum);
                sum = 0;
            }
            else if((arr[i] == 127) && (i < arr.length - 1) && (arr[i + 1] != 0))
            {
                ListArr.add(sum);
                sum = 0;
            }
            else if((i == 12) && (arr[i] == 0))
            {
                ListArr.add(0);
            }
        }
        return ListArr;
    }

}
