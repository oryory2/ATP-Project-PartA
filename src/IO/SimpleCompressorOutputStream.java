package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream
{
    private OutputStream out;
    private int lastByte = 0;
    private int counter;

    public void write(int b) throws IOException
    {
        if(b == this.lastByte)
            this.counter++;
        else
        {
            byte[] arr = new byte[counter];
            for(int i = 0; i < counter; i ++)
            {
                arr[i] = lastByte;
            }
            this.out.write(arr);
            lastByte = ((byte)b);
            counter = 1;
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
            if((b[i] == 0) && (flag == false))
            {
                counter++;
                if(counter == 128)
                {
                    this.out.write(127);
                    this.out.write(0);
                    counter = 1;
                }
            }
            else if((b[i] == 1) && (flag == true))
            {
                counter++;
                if(counter == 128)
                {
                    this.out.write(127);
                    this.out.write(0);
                    counter = 1;
                }
            }
            else if((b[i] == 0) && (flag == true))
            {
                this.out.write(counter);
                counter = 1;
                flag = false;
            }
            else if((b[i] == 1) && (flag == false))
            {
                this.out.write(counter);
                counter = 1;
                flag = true;
            }
        }
        if(counter != 0)
            this.out.write(counter);
    }
}

/*public byte[] toByteArray()
    {
        ArrayList<Integer> IntegerCompressed = new ArrayList<Integer>(); // [row (0) row (1), column(2) column(3), s(4) s(5), s(6) s(7), f(8) f(9), f(10) f(11), ....................]

        int row = this.max_rows;
        int column = this.max_columns;

        updateArrayList(row, IntegerCompressed);
        updateArrayList(column, IntegerCompressed);

        Position s = this.getStartPosition();
        Position f = this.getGoalPosition();

        updateArrayList(s.getRowIndex(), IntegerCompressed);
        updateArrayList(s.getColumnIndex(), IntegerCompressed);
        updateArrayList(f.getColumnIndex(), IntegerCompressed);
        updateArrayList(f.getColumnIndex(), IntegerCompressed);

        int[][] mazeArr = this.getMazeArr();
        boolean flag = false; // true = 1, false = 0
        int counter = 0;
        for(int i = 0 ; i < mazeArr.length; i++)
        {
            for (int j = 0; j < mazeArr[0].length; j++)
            {
                if((mazeArr[i][j] == 0) && (flag == false))
                {
                    counter++;
                    if(counter == 128)
                    {
                        IntegerCompressed.add(127);
                        IntegerCompressed.add(0);
                        counter = 1;
                    }
                }
                else if((mazeArr[i][j] == 1) && (flag == true))
                {
                    counter++;
                    if(counter == 128)
                    {
                        IntegerCompressed.add(127);
                        IntegerCompressed.add(0);
                        counter = 1;
                    }
                }
                else if((mazeArr[i][j] == 0) && (flag == true))
                {
                    IntegerCompressed.add(counter);
                    counter = 1;
                    flag = false;
                }
                else if((mazeArr[i][j] == 1) && (flag == false))
                {
                    IntegerCompressed.add(counter);
                    counter = 1;
                    flag = true;
                }
            }
        }
        if(counter != 0)
            IntegerCompressed.add(counter);
        return ArrayToByte(IntegerCompressed);
    }

    private static void updateArrayList(int num, ArrayList<Integer> Arr)
    {
        if(Arr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }

        if(num <= 127)
        {
            Arr.add(0);
            Arr.add(num);
        }
        else
        {
            Arr.add(num / 10);
            Arr.add(num % 10);
        }
    }

    private static byte[] ArrayToByte(ArrayList<Integer> Arr)
    {
        if(Arr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }

        byte[] byteArr = new byte[Arr.size()];
        for(int i = 0 ; i < Arr.size(); i++)
        {
            int n = Arr.get(i);
            byteArr[i] = ((byte)n);
        }
        return byteArr;
    }
*/
}



