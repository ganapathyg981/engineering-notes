package graph;

import java.util.Arrays;

public class DisjointSet1 {
    private int[] parent;
    private int[] rank;  // Auxiliary array to keep track of the rank (depth) of trees

    // Constructor to initialize the disjoint set
    public DisjointSet1(int n) {
        parent = new int[n];
        rank = new int[n]; // Initialize all ranks to 0
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Initially, each element is its own parent (it's a root)
            rank[i] = 0;    // Rank of a single node tree is 0
        }
    }

    // Find with path compression
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);  // Path compression
        }
        return parent[node];
    }

    // Union by rank
    public boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            return false;  // They are already in the same set
        }

        // Union by rank
        if (rank[root1] > rank[root2]) {
            parent[root2] = root1;  // root1 becomes the parent of root2
        } else if (rank[root1] < rank[root2]) {
            parent[root1] = root2;  // root2 becomes the parent of root1
        } else {
            // If ranks are the same, pick one as the parent and increase its rank
            parent[root2] = root1;
            rank[root1]++;
        }

        return true;
    }

    // Optional: To print the current state of the sets
    public void printSet() {
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Rank: " + Arrays.toString(rank));
    }

}