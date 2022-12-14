package com.company;
import javax.swing.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n for n*n chess board: ");
        int n = input.nextInt();


        for (int c = 0; c < n; c++) {
            int col = c;

            int[][] board = new int[n][n];
            // initializing board with 0s
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    board[i][j] = 0;

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame();
                    frame.setSize(700, 700);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setTitle("N QUEENS PROBLEM");
                    frame.setVisible(true);

                    solveNQ(frame, board, n, 0, col);
                }
            });
            t.start();
        }
    }



    public static void printSolution(int[][] board, int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public static boolean isSafe(int[][] board, int n, int row, int col)
    {
        int i, j;

        /* left side */

        // Check this row on left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < n; i++, j--)
            if (board[i][j] == 1)
                return false;

        /* right side */

        // Check this row on right side
        for (i = col+1; i < n; i++)
            if (board[row][i] == 1)
                return false;

        // Check upper diagonal on right side
        for (i = row, j = col; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal on right side
        for (i = row, j = col; j < n && i < n; i++, j++)
            if (board[i][j] == 1)
                return false;

        /* columns */

        // Check the upper half of this column
        for (i = row-1; i >= 0; i--)
            if (board[i][col] == 1)
                return false;

        // Check the lower half of this column
        for (i = row+1; i < n; i++)
            if (board[i][col] == 1)
                return false;


        return true;
    }

    public static boolean solveNQ(JFrame frame, int[][] board, int n, int row, int col)
    {

        // base case: If all queens are placed then return true
        if (row >= n)
            return true;

        int i = 0;
        if(row == 0){ // if it's the first row make the thread start from column col
            i = col;
        }
        for ( ; i < n; i++) {

            // updating the frame
            frame.setContentPane(new UI(board, n).myPanel());
            frame.setVisible(true);

            // Check if the queen can be placed on board[row][i]
            if (isSafe(board, n, row, i)) {

                board[row][i] = 1;

                // updating the frame
                frame.setContentPane(new UI(board, n).myPanel());
                frame.setVisible(true);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // recur to place rest of the queens
                if (solveNQ(frame, board, n, row + 1, col))
                    return true;

                /* If placing queen in board[i][col]
                    doesn't lead to a solution, then
                    remove queen from board[i][col] */

                // updating the frame
                frame.setContentPane(new UI(board, n).myPanel());
                frame.setVisible(true);

                board[row][i] = 0; // BACKTRACK

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(row == 0) { // terminate when we backtrack to the first row
                    break;
                }
            }
        }

        /* If the queen cannot be placed in any row in
            this row "row"  then return false */
        return false;
    }


}
