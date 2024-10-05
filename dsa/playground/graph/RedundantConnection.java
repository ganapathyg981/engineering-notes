package graph;

import java.util.*;

public class RedundantConnection {

    public static int[] findRedundantConnection(int[][] edges) {
        DisjointSet1 disjointSet = new DisjointSet1(edges.length+1); //TO CHECK
        for (int [] edge: edges) {
            boolean union = disjointSet.union(edge[0], edge[1]);
            if(!union) {
                return edge;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
//         Test case 1: General case
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

        int[][] edges3 = {{1,4},{3,4},{1,3},{1,2},{4,5}};

        System.out.println("Test case 2: " + Arrays.toString(findRedundantConnection(edges3))); // Expected: [1, 4]

    }
}