package graph;

import java.util.*;

public class RedundantConnection {

    public static int[] findRedundantConnection(int[][] edges) {
        // Your solution here
        return new int[0];
    }

    public static void main(String[] args) {
        // Test case 1: General case
        int[][] edges1 = {
            {1, 2},
            {1, 3},
            {2, 3}
        };
        System.out.println("Test case 1: " + Arrays.toString(findRedundantConnection(edges1))); // Expected: [2, 3]

        // Test case 2: Another case
        int[][] edges2 = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 4},
            {1, 5}
        };
        System.out.println("Test case 2: " + Arrays.toString(findRedundantConnection(edges2))); // Expected: [1, 4]
    }
}