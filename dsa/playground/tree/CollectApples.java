package tree;

import java.util.*;

public class CollectApples {

    public static void main(String[] args) {
        // Define the input values for testing
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> hasApple = Arrays.asList(false, false, true, false, true, true, false);

        // Call the solution method and print the result
        int result = minTime(n, edges, hasApple);
        System.out.println("Minimum time to collect all apples: " + result);
    }

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> adjList = getAdjList(edges, n);
        Set<Integer> visited = new HashSet<>();
        int dfs = dfs(adjList, visited, hasApple, 0);
        return dfs; // Placeholder return value
    }

    private static int dfs(List<List<Integer>> adjList, Set<Integer> visited, List<Boolean> hasApple, Integer current) {
        int result = 0;
        visited.add(current);
        for (Integer neighbor:
             adjList.get(current)) {
            if(!visited.contains(neighbor)) {
                 result += dfs(adjList, visited, hasApple, neighbor);
            }
        }
        if(current ==0 ){
            return result;
        }
        return result + (hasApple.get(current) || result > 0 ? 2 : 0);  // accumumalore, donr just return answers , use previous answers
    }

    private static List<List<Integer>> getAdjList(int[][] edges, int n) {
        List<List<Integer>> out = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
                out.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
                out.get(edges[i][0]).add(edges[i][1]);
            out.get(edges[i][1]).add(edges[i][0]);
        }
        return out;
    }
}
