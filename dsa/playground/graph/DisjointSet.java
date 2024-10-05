package graph;

import java.util.Arrays;

public class DisjointSet {
    Integer[] parent;

    DisjointSet(int n) {
        parent = new Integer[n];
        Arrays.fill(parent, -1);
    }

    public int find(int node) {
        int value = parent[node];
        if(value < 0) {
            return node;
        }
        return find(value);
    }

    public boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        System.out.print(node1 +" parent "+root1 +"------");
        System.out.println(node2 +" parent "+root2);

        if(root1 == root2) {
            return false;
        }
        if (parent[root1] < parent[root2]) {
            parent[node2] = root1;
            parent[root1]--;
        } else {
            parent[node1] = root2;
            parent[root2]--;
        }
        return true;
    }

    public void printSet() {
        System.out.println(Arrays.deepToString(parent));
    }
}
