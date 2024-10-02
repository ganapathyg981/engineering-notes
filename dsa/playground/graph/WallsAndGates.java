package graph;

import java.util.*;

public class WallsAndGates {

    public static void wallsAndGates(int[][] rooms) {
        // Your solution here
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;

        // Test case 1: General case
        int[][] rooms1 = {
            {INF, -1, 0, INF},
            {INF, INF, INF, -1},
            {INF, -1, INF, -1},
            {0, -1, INF, INF}
        };
        wallsAndGates(rooms1);
        System.out.println("Test case 1: " + Arrays.deepToString(rooms1)); 
        // Expected: [[3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4]]

        // Test case 2: All walls
        int[][] rooms2 = {
            {-1, -1},
            {-1, -1}
        };
        wallsAndGates(rooms2);
        System.out.println("Test case 2: " + Arrays.deepToString(rooms2)); // Expected: unchanged

        // Test case 3: Single room (gate)
        int[][] rooms3 = {
            {0}
        };
        wallsAndGates(rooms3);
        System.out.println("Test case 3: " + Arrays.deepToString(rooms3)); // Expected: unchanged
    }
}