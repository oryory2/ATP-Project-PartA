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
        if(num <= 127)
        {
            Arr.add(0);
            Arr.add(num);
        }
        else if (num != 1000)
        {
            Arr.add(num % 100);
            Arr.add(num - ((num % 100) * 100));
        }
        else
        {
            Arr.add(1);
            Arr.add(000);
        }
    }

    private static byte[] ArrayToByte(ArrayList<Integer> Arr)
    {
        byte[] byteArr = new byte[Arr.size()];
        for(int i = 0 ; i < Arr.size(); i++)
        {
            int n = Arr.get(i);
            byteArr[i] = ((byte)n);
        }
        return byteArr;
    }
}
