package graph;

import java.util.*;
/*
You are given a 2-D matrix board containing 'X' and 'O' characters.

If a continous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.

Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.
 */
public class SurroundedRegions {

    static int[][] moveOffset = {{0,1}, {0,-1},{1,0},{-1,0}};
    public static void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i=0;i<board[0].length;i++) {
            dfsHelper(0, i, visited, board);
            dfsHelper(board.length-1, i, visited, board);
        }
        for(int i=0;i<board.length;i++) {
            dfsHelper(i, 0, visited, board);
            dfsHelper(i, board[i].length-1, visited, board);
        }
        System.out.println(Arrays.deepToString(board));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
               if(board[i][j]=='O') {
                   board[i][j]='X';
               }
                if(board[i][j]=='#') {
                    board[i][j]='O';
                }
            }
        }
    }

    private static void dfsHelper(int row, int column, boolean[][] visited, char[][] board) {
        if(board[row][column]!='O' || visited[row][column]) {
            return;
        }
        board[row][column]='#';
        visited[row][column] = true;
        for(int[] offset:moveOffset) {
            int nextRow = row+offset[0];
            int nextColumn = column+offset[1];
            if (nextRow >= 0 && nextRow<board.length && nextColumn >= 0
                    && nextColumn<board[nextRow].length ) {
                dfsHelper(nextRow,nextColumn, visited, board);
            }

        }

    }

    public static void main(String[] args) {
        // Test case 1: General case
        char[][] board1 = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        solve(board1);
        System.out.println(Arrays.deepToString(board1)); // Expected: [['X', 'X', 'X', 'X'], ['X', 'X', 'X', 'X'], ['X', 'X', 'X', 'X'], ['X', 'O', 'X', 'X']]

//         Test case 2: Edge case
        char[][] board2 = {
            {'O'}
        };
        solve(board2);
        System.out.println(Arrays.deepToString(board2)); // Expected: [['O']]
    }
}