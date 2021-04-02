package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {
    private int[][][] mazeArr;
    private int max_rows;
    private int max_columns;
    private int max_depth;
    private Position3D StartPosition;
    private Position3D GoalPosition;


    public Maze3D(int[][][] mazeArr) {
        this.mazeArr = mazeArr;

        this.max_depth = mazeArr.length;
        this.max_rows = mazeArr[0].length;
        this.max_columns = mazeArr[0][0].length;
        this.StartPosition = new Position3D(0, 0, 0);
        this.GoalPosition = new Position3D(mazeArr.length - 1, mazeArr[0].length - 1, mazeArr[0][0].length - 1);
    }

    public int[][][] getMap() {
        return mazeArr;
    }

    public int getMax_rows() {
        return max_rows;
    }

    public int getMax_columns() {
        return max_columns;
    }

    public int getMax_depth() {
        return max_depth;
    }

    public Position3D getStartPosition() {
        return StartPosition;
    }

    public Position3D getGoalPosition() {
        return GoalPosition;
    }


    public void print()
    {
        System.out.println("{");
        System.out.print("{ S ");
        for (int i = 1; i < mazeArr[0][0].length; i++) {
            if (i != mazeArr[0][0].length - 1)
                System.out.print(mazeArr[0][0][i] + " ");
            else
                System.out.println(mazeArr[0][0][i] + " }");
        }
        for (int i = 1; i < this.mazeArr[0].length - 1; i++) // Middle Rows
        {
            System.out.print("{ ");
            for (int j = 0; j < mazeArr[0][0].length; j++) {
                if (j != mazeArr[0][0].length - 1)
                    System.out.print(mazeArr[0][i][j] + " ");
                else
                    System.out.println(mazeArr[0][i][j] + " }");
            }
        }
        if (mazeArr.length == 1) // only one 2D array
        {
            System.out.print("{ ");
            for (int t = 0; t < mazeArr[0][0].length; t++) // Last Row
            {
                if (t != mazeArr[0][0].length - 1)
                    System.out.print(mazeArr[0][mazeArr[0].length - 1][t] + " ");
                else
                    System.out.println("E }");
            }
            System.out.println("}");
        }
        else // more than one 2D array
        {
            System.out.print("{ ");
            for (int t = 0; t < mazeArr[0][0].length; t++)
            {
                if (t != mazeArr[0][0].length - 1)
                    System.out.print(mazeArr[0][mazeArr[0].length - 1][t] + " ");
                else
                    System.out.println(mazeArr[0][mazeArr[0].length - 1][t] + " }");
            }
            int counter = 0;
            while (counter != mazeArr.length - 1)
            {
                counter++;
                System.out.println("-------------");

                for (int i = 0; i < this.mazeArr[0].length; i++)
                {
                    System.out.print("{ ");
                    for (int j = 0; j < mazeArr[0][0].length; j++) {
                        if (j != mazeArr[0][0].length - 1)
                            System.out.print(mazeArr[counter][i][j] + " ");
                        else {
                            if ((counter == mazeArr.length - 1) && (i == mazeArr[0].length - 1))
                                System.out.println("E }");
                            else
                                System.out.println(mazeArr[counter][i][j] + " }");
                        }
                    }
                }
            }
            System.out.println("}");
        }
    }
}