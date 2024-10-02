package graph;

import java.util.*;

public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public static int countComponents(int n, int[][] edges) {
        // Your solution here
        return 0;
    }

    public static void main(String[] args) {
        // Test case 1: Two components
        int[][] edges1 = {
            {0, 1},
            {1, 2},
            {3, 4}
        };
        System.out.println("Test case 1: " + countComponents(5, edges1)); // Expected: 2

        // Test case 2: Single component
        int[][] edges2 = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 4}
        };
        System.out.println("Test case 2: " + countComponents(5, edges2)); // Expected: 1

        // Test case 3: No edges
        System.out.println("Test case 3: " + countComponents(3, new int[0][])); // Expected: 3
    }
}