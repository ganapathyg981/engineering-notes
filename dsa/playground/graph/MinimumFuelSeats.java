package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumFuelSeats {

    private long totalFuel;

    public long minimumFuelCost(int[][] roads, int seats) {
        totalFuel =0;
        // Step 1: Build the adjacency list for the tree
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] road : roads) {
            adjList.computeIfAbsent(road[0], k -> new ArrayList<>()).add(road[1]);
            adjList.computeIfAbsent(road[1], k -> new ArrayList<>()).add(road[0]);
        }

        // Step 2: Start DFS from the capital (node 0) to calculate fuel costs
        dfs(0,-1,  adjList, seats);

        // Step 3: Return the total fuel cost
        return totalFuel;
    }

    private int dfs(int current,int previous, Map<Integer, List<Integer>> adjList, int seats) {
        int people=1;
        adjList.get(current);
        for(int node : adjList.get(current)) {
            if(node != previous) {
                people+=dfs(node, current, adjList,seats);
            }
        }
        if(current>0) {
            totalFuel+=(people+seats-1)/seats;
        }
        return people;

    }

    public static void main(String[] args) {
        MinimumFuelSeats minimumFuelSeats = new MinimumFuelSeats();
        int[][] roads = {{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}};
        int seats = 2;

        long fuelCost = minimumFuelSeats.minimumFuelCost(roads, seats);
        System.out.println(fuelCost);

    }
}
