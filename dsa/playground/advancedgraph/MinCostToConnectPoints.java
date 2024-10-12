package advancedgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MinCostToConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        Map<Integer, List<List<Integer>>> allEdges = getAllEdges(points);
        PriorityQueue<List<Integer>> queue
                = new PriorityQueue<>(Comparator.comparing(x->x.get(1)));
        queue.add(List.of(0,0));
        int edgesPicked=1;
        int res=0;
        Set<Integer> visited = new HashSet<>();
        while(edgesPicked <= allEdges.size()) {
            List<Integer> current = queue.poll();
            int currentWeight = current.get(1);
            int currentNode = current.get(0);
            if(visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            res+=currentWeight;
            edgesPicked++;
            List<List<Integer>> neighbours = allEdges.get(currentNode);
            for (List<Integer> nextEdge: neighbours) {
                queue.offer(nextEdge);
            }
        }
        return res; // Placeholder return statement
    }

    private Map<Integer, List<List<Integer>>> getAllEdges(int[][] points) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            for(int j=i+1;j<points.length;j++) {
                map.putIfAbsent(i, new ArrayList<>());
                map.putIfAbsent(j, new ArrayList<>());
                int dest = length(points[i], points[j]);
                map.get(i).add(List.of(j,dest));
                map.get(j).add(List.of(i,dest));
            }
        }
        return map;
    }

    private int length(int[] src, int[] dest) {
        return Math.abs(src[0] - dest[0]) + Math.abs(src[1] - dest[1]);
    }

    public static void main(String[] args) {
        MinCostToConnectPoints solution = new MinCostToConnectPoints();

        // Test case 1: Simple case with points in a square
        int[][] points1 = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        System.out.println("Test Case 1: " + solution.minCostConnectPoints(points1)); // Expected output: 3

        // Test case 2: Points in a straight line
        int[][] points2 = {{0, 0}, {0, 2}, {0, 4}, {0, 6}};
        System.out.println("Test Case 2: " + solution.minCostConnectPoints(points2)); // Expected output: 6

        // Test case 3: Random points
        int[][] points3 = {{1, 1}, {3, 4}, {-1, 0}, {0, 2}};
        System.out.println("Test Case 3: " + solution.minCostConnectPoints(points3)); // Expected output: 7

        // Test case 4: Two points
        int[][] points4 = {{0, 0}, {1, 1}};
        System.out.println("Test Case 4: " + solution.minCostConnectPoints(points4)); // Expected output: 2

        // Test case 5: Large set of points
        int[][] points5 = {{0, 0}, {1, 2}, {3, 1}, {4, 4}, {5, 5}, {6, 6}};
        System.out.println("Test Case 5: " + solution.minCostConnectPoints(points5)); // Expected output: 12
    }
}