package advancedgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlights {

    // Solution method placeholder
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Integer[] distance = new Integer[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(List.of(flight[1], flight[2]));
        }
        PriorityQueue<List<Integer>> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
        priorityQueue.add(List.of(src,0));
        distance[src] = 0;
        int level = 0;
        while (!priorityQueue.isEmpty() && level<=k) {
            int levelSize = priorityQueue.size();
            for(int i=0;i<levelSize;i++) {
                List<Integer> edge = priorityQueue.poll();
                int neighbor = edge.get(0);
                int cDistance = edge.get(1);

                if(cDistance > distance[neighbor]) {
                    continue;
                }
                if(map.containsKey(neighbor)) {
                    for(List<Integer> edges : map.get(neighbor)) {
                        int newDistance = cDistance+edges.get(1);
                        if (newDistance < distance[edges.get(0)]) {
                            distance[edges.get(0)] = newDistance;
                            priorityQueue.offer(List.of(edges.get(0),newDistance));
                        }
                    }
                }
            }
            level++;
        }

        return distance[dst]==Integer.MAX_VALUE?-1:distance[dst]; // Placeholder return value
    }

    public static void main(String[] args) {
        // Test 1
        int[][] flights1 = { {0, 1, 100}, {1, 2, 100}, {0, 2, 500} };
        int n1 = 3;
        int src1 = 0;
        int dst1 = 2;
        int k1 = 1;
        int expectedOutput1 = 200; // Adjust after implementation
        int result1 = findCheapestPrice(n1, flights1, src1, dst1, k1);
        System.out.println("Test 1: " + (result1 == expectedOutput1 ? "Passed" : "Failed"));

        // Test 2
        int[][] flights2 = { {0, 1, 100}, {1, 2, 100}, {0, 2, 500} };
        int n2 = 3;
        int src2 = 0;
        int dst2 = 2;
        int k2 = 0;
        int expectedOutput2 = 500; // Adjust after implementation
        int result2 = findCheapestPrice(n2, flights2, src2, dst2, k2);
        System.out.println("Test 2: " + (result2 == expectedOutput2 ? "Passed" : "Failed"));

        // Test 3
        int[][] flights3 = { {0, 1, 100}, {1, 3, 100}, {1, 2, 100}, {2, 3, 100}, {0, 3, 500} };
        int n3 = 4;
        int src3 = 0;
        int dst3 = 3;
        int k3 = 2;
        int expectedOutput3 = 200; // Adjust after implementation
        int result3 = findCheapestPrice(n3, flights3, src3, dst3, k3);
        System.out.println("Test 3: " + (result3 == expectedOutput3 ? "Passed" : "Failed"));

        // Add more tests as needed
    }
}