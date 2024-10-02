package graph;

import java.util.*;

public class MaxAreaOfIsland {

    public static int maxAreaOfIsland(int[][] grid) {
        // Your solution here
        return 0;
    }

    public static void main(String[] args) {
        // Test case 1: General case
        int[][] grid1 = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}
        };
        System.out.println("Test case 1: " + maxAreaOfIsland(grid1)); // Expected: 6

        // Test case 2: Single cell island
        int[][] grid2 = {
            {1}
        };
        System.out.println("Test case 2: " + maxAreaOfIsland(grid2)); // Expected: 1

        // Test case 3: No islands
        int[][] grid3 = {
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Test case 3: " + maxAreaOfIsland(grid3)); // Expected: 0
    }
}