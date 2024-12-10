package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformEmployees {

    public static void main(String[] args) {
//        // Test case 1
//        int n1 = 6;
//        int headID1 = 2;
//        int[] manager1 = {2, 2, -1, 2, 2, 2};
//        int[] informTime1 = {0, 0, 1, 0, 0, 0};
//        System.out.println("Test Case 1: " + numOfMinutes(n1, headID1, manager1, informTime1)); // Expected output: 1
//
//        // Test case 2
//        int n2 = 7;
//        int headID2 = 6;
//        int[] manager2 = {1, 2, 3, 4, 5, 6, -1};
//        int[] informTime2 = {0, 6, 5, 4, 3, 2, 1};
//        System.out.println("Test Case 2: " + numOfMinutes(n2, headID2, manager2, informTime2)); // Expected output: 21
//
//        // Test case 3
//        int n3 = 1;
//        int headID3 = 0;
//        int[] manager3 = {-1};
//        int[] informTime3 = {0};
//        System.out.println("Test Case 3: " + numOfMinutes(n3, headID3, manager3, informTime3)); // Expected output: 0

        // Test case 4
        int n4 = 4;
        int headID4 = 0;
        int[] manager4 = {-1, 0, 0, 1};
        int[] informTime4 = {2, 1, 1, 0};
        System.out.println("Test Case 4: " + numOfMinutes(n4, headID4, manager4, informTime4)); // Expected output: 3

    }

    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> adjacencyList = createAdjacencyList(n, manager);
        return getResult(adjacencyList, informTime, headID,0);
    }


    private static int getResult(Map<Integer, List<Integer>> adjacencyList, int[] informTime, int current, int currentTime) {
        // Base case: if the current node has no subordinates, return the current time
        List<Integer> neighbors = adjacencyList.get(current);
        currentTime = currentTime + informTime[current];
        if (neighbors == null || neighbors.isEmpty()) {
            System.out.println(currentTime);
            return currentTime;
        }

        int maxTime = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            int subordinate = neighbors.get(i);
            // Recursively calculate the time for each subordinate
            maxTime = Math.max(maxTime, getResult(adjacencyList, informTime, subordinate, currentTime));
        }
        return maxTime;
    }

    public static Map<Integer, List<Integer>> createAdjacencyList(int n, int[] manager) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adjList.putIfAbsent(manager[i], new ArrayList<>());
                adjList.get(manager[i]).add(i);
            }
        }
        return adjList;
    }
}
