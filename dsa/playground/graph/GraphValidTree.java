package graph;

import java.util.*;

public class GraphValidTree {

    public static boolean validTree(int n, int[][] edges) {
        // Your solution here
        return false;
    }

    public static void main(String[] args) {
        // Test case 1: Valid tree
        int[][] edges1 = {
            {0, 1},
            {0, 2},
            {0, 3},
            {1, 4}
        };
        System.out.println("Test case 1: " + validTree(5, edges1)); // Expected: true

        // Test case 2: Invalid tree (cycle)
        int[][] edges2 = {
            {0, 1},
            {1, 2},
            {2, 3},
            {1, 3},
            {1, 4}
        };
        System.out.println("Test case 2: " + validTree(5, edges2)); // Expected: false

        // Test case 3: Disconnected graph
        int[][] edges3 = {
            {0, 1},
            {2, 3}
        };
        System.out.println("Test case 3: " + validTree(4, edges3)); // Expected: false
    }
}