package advancedgraph;

import java.util.*;

public class NetworkDelayTime {

    // Solution method placeholder
    public static int networkDelayTime(int[][] times, int n, int k) {
        Integer[] distance = new Integer[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new ArrayList<>());
            map.get(time[0]).add(List.of(time[1], time[2]));
        }
        PriorityQueue<List<Integer>> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
        priorityQueue.add(List.of(k,0));
        distance[k] = 0;

        while (!priorityQueue.isEmpty()) {
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
        System.out.println(Arrays.deepToString(distance));
        int max = Integer.MIN_VALUE;
        for (int i=1; i<distance.length; i++) {
            max = Math.max(distance[i], max);
        }
        return max==Integer.MAX_VALUE?-1:max; // Placeholder return value
    }

    public static void main(String[] args) {
        // Test 1
        int[][] times1 = { {2, 1, 1}, {2, 3, 1}, {3, 4, 1} };
        int n1 = 4;
        int k1 = 2;
        int expectedOutput1 = 2;  // Adjust this after implementation
        int result1 = networkDelayTime(times1, n1, k1);
        System.out.println("Test 1: " + (result1 == expectedOutput1 ? "Passed" : "Failed"));

        // Test 2
        int[][] times2 = { {1, 2, 1}, {2, 3, 2}, {1, 3, 4} };
        int n2 = 3;
        int k2 = 1;
        int expectedOutput2 = 3;  // Adjust this after implementation
        int result2 = networkDelayTime(times2, n2, k2);
        System.out.println("Test 2: " + (result2 == expectedOutput2 ? "Passed" : "Failed"));

        // Test 3
        int[][] times3 = { {1, 2, 1}, {2, 3, 2}, {3, 4, 3}, {4, 5, 4} };
        int n3 = 5;
        int k3 = 1;
        int expectedOutput3 = 10;  // Adjust this after implementation
        int result3 = networkDelayTime(times3, n3, k3);
        System.out.println("Test 3: " + (result3 == expectedOutput3 ? "Passed" : "Failed"));
        // Add more tests as needed

        // Test 4
        int[][] times4 = { {1,2,1},{2,3,1},{1,4,4},{3,4,1} };
        int n4 = 4;
        int k4 = 1;
        int expectedOutput4 = 3;  // Adjust this after implementation
        int result4 = networkDelayTime(times4, n4, k4);
        System.out.println(result4);
        System.out.println("Test 3: " + (result4 == expectedOutput4 ? "Passed" : "Failed"));
        // Add more tests as needed
    }
}