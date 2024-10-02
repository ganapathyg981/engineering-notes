package graph;

import java.util.*;

public class SurroundedRegions {

    public static void solve(char[][] board) {
        // Your solution here
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

        // Test case 2: Edge case
        char[][] board2 = {
            {'O'}
        };
        solve(board2);
        System.out.println(Arrays.deepToString(board2)); // Expected: [['O']]
    }
}