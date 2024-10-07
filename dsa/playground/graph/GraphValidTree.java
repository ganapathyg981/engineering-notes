package graph;

import java.util.*;

public class GraphValidTree {

    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            // A valid tree should have exactly n-1 edges
            return false;
        }
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i=0;i<n;i++) { // This is best
            adj.put(i, new ArrayList<>());
        }

        for (int [] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        // Perform DFS from node 0 (assuming 0 is part of the graph)
        if (!dfs(visited, 0, adj, -1)) {
            return false; // Cycle detected
        }
        return visited.size() == n;
    }

    public static boolean dfs(Set<Integer> visited, int node, Map<Integer,List<Integer>> map, int parent) {
        visited.add(node); // Always best to have on top
        for (int neighbor : map.get(node)) {
            if(neighbor == parent) {
                continue;
            }
            if(!visited.contains(neighbor)) {
                boolean dfs = dfs(visited, neighbor, map, node);
                if(!dfs) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
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