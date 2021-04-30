package algorithms.mazeGenerators;

import algorithms.search.AState;

import java.util.ArrayList;

public class Maze /** This Class describe a Maze from any Shape */

{
    private Position StartPosition;
    private Position GoalPosition;
    private int [][] mazeArr;
    private int max_rows;
    private int max_columns;

     /**
     * constructor
     * setting the StartPosition of the Maze to be {0, 0}
     * setting the GoalPosition of the Maze to be {mazeArr.length - 1, mazeArr[0].length - 1}
     * @param mazeArr the 2DArray that presenting the Maze
     */
    public Maze(int [][] mazeArr)
    {
        if(mazeArr == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
        if((mazeArr.length <= 1) || (mazeArr[0].length <= 1))
        {
            throw new RuntimeException("The Array that supplied is not legal! it must have at least 2 Rows and 2 Columns");
        }
        this.mazeArr = mazeArr;
        // we chose the StartPosition to be (0,0)
        this.StartPosition = new Position(0,0);
        // we chose the GoalPosition to be (mazeArr.length - 1, mazeArr[0].length - 1)
        this.GoalPosition = new Position(mazeArr.length - 1, mazeArr[0].length - 1);
        this.max_rows = mazeArr.length;
        this.max_columns = mazeArr[0].length;
    }

    public Maze(byte [] b) // [row (0) row (1), column(2) column(3), s(4) s(5), s(6) s(7), f(8) f(9), f(10) f(11), ....................]
    {
        if(b == null)
        {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
        this.max_rows = bytesToInt(b[0], b[1]);
        this.max_columns = bytesToInt(b[2], b[3]);
        this.StartPosition = new Position(bytesToInt(b[4], b[5]), bytesToInt(b[6], b[7]));
        this.GoalPosition = new Position(bytesToInt(b[8], b[9]), bytesToInt(b[10], b[11]));

        if((this.max_rows <= 1) || (this.max_columns <= 1))
        {
            throw new RuntimeException("The Array that supplied is not legal! it must have at least 2 Rows and 2 Columns");
        }

        int [][] mazeArr = new int[this.max_rows][this.max_columns];
        int thisIndex = 12;
        for(int i = 0; i < mazeArr.length; i++)
        {
            for(int j = 0; j < mazeArr[0].length; j++)
            {
                mazeArr[i][j] = b[thisIndex];
                thisIndex++;
            }
        }
        this.mazeArr = mazeArr;
    }

    private static int bytesToInt(byte a, byte b)
    {
        String one = String.valueOf(a);
        String two = String.valueOf(b);
        String c = one + two;
        return Integer.parseInt(c);
    }

     /**
     * @return the StartPosition of the maze (Position)
     */
    public Position getStartPosition()
    {
        return this.StartPosition;
    }

     /**
     * @return the GoalPosition of the maze (Position)
     */
    public Position getGoalPosition()
    {
        return this.GoalPosition;
    }

     /**
     * Printing method for the Maze
     * Printing the maze according to the 2DArray representing it
     */
    public void print()
    {

        System.out.println("{");
        System.out.print("{ S ");
        for (int k = 1; k < mazeArr[0].length; k++) // First Row
        {
            if(k != mazeArr[0].length - 1)
                System.out.print(mazeArr[0][k] + " ");
            else
                System.out.println(mazeArr[0][k] + " }");
        }
        for(int i = 1; i < this.mazeArr.length - 1; i++) // Middle Rows
        {
            System.out.print("{ ");
            for (int j = 0; j < mazeArr[0].length; j++)
            {
                if(j != mazeArr[0].length - 1)
                    System.out.print(mazeArr[i][j] + " ");
                else
                    System.out.println(mazeArr[i][j] + " }");
            }
        }
        System.out.print("{ ");
        for (int t = 0; t < mazeArr[0].length; t++) // Last Row
        {
            if(t != mazeArr[0].length - 1)
                System.out.print(mazeArr[mazeArr.length - 1][t] + " ");
            else
                System.out.println("E }");
        }
        System.out.println("}");
    }

     /**
     * @return the 2DArray representing the maze (int [][])
     */
    public int [][] getMazeArr()
    {
        return this.mazeArr;
    }

     /**
     * @return the number of rows in the maze (int)
     */
    public int getMax_rows() {
        return max_rows;
    }

     /**
     * @return the number of columns in the maze (int)
     */
    public int getMax_columns() {
        return max_columns;
    }

    public byte[] toByteArray()
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

        for(int i = 0; i < this.mazeArr.length; i++)
        {
            for(int j = 0; j < this.mazeArr[0].length; j++)
            {
                IntegerCompressed.add(this.mazeArr[i][j]);
            }
        }
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
}
