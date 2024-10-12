package advancedgraph;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SwimInRisingWater {

    public static void main(String[] args) {
        int[][] grid = {
            {0, 2, 1},
            {3, 4, 6},
            {5, 7, 8}
        };
        SwimInRisingWater swimInRisingWater = new SwimInRisingWater();

        // Call the swimInWater method and print the result
        System.out.println(swimInRisingWater.swimInWater(grid));
    }

    int[][] offsets = {{0,1},{1,0},{-1,0},{0,-1}};

    public int swimInWater(int[][] grid) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        queue.add(new Node(grid[0][0],0,0));
        Set<Node> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            Node  node = queue.poll();
            if(isWithinBounds(node.row,node.col, grid) &&
                    node.row == grid.length-1 &&
                    node.col == grid[0].length-1) {
                return node.weight;
            }
            for (int[] offset: offsets) {
                if(isWithinBounds(node.row+offset[0],node.col+offset[1], grid)) {
                    int max = Math.max(node.weight, grid[node.row + offset[0]][node.col + offset[1]]);
                    Node newNode = new Node(max, node.row + offset[0], node.col + offset[1]);
                    if(!visited.contains(newNode)) {
                        queue.add(newNode);
                        visited.add(newNode);
                    }
                }
            }
        }
        return 0;  // Replace this with your logic
    }

    private boolean isWithinBounds(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }



}
class Node {
    int weight;
    int row;
    int col;
    Node(int weight, int row, int col) {
        this.weight = weight;
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;  // Simple hash function based on row and col
    }
}