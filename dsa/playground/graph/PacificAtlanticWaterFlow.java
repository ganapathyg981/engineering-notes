package graph;

import java.util.*;

public class PacificAtlanticWaterFlow {

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Your solution here
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Test case 1: General case
        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        System.out.println("Test case 1: " + pacificAtlantic(heights1)); // Expected: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        // Test case 2: Single cell
        int[][] heights2 = {
            {1}
        };
        System.out.println("Test case 2: " + pacificAtlantic(heights2)); // Expected: [[0,0]]

        // Test case 3: Flat surface
        int[][] heights3 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        System.out.println("Test case 3: " + pacificAtlantic(heights3)); // Expected: All cells
    }
}