package graph;

import java.util.*;

public class CourseSchedule {

    // Enum for the state of each course
    enum State {
        NOT_VISITED, VISITING, VISITED
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        State[] state = new State[numCourses];
        Arrays.fill(state, State.NOT_VISITED);
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Stack<Integer> topoSort = new Stack<>();

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            adj.get(edge[1]).add(edge[0]);
        }

        // Perform DFS and detect cycles
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == State.NOT_VISITED) {
                if (!dfs(state, i, adj, topoSort)) {
                    return false; // Cycle detected
                }
            }
        }

        // Print topological sort order
        System.out.println("Topological Sort: " + topoSort);
        return true;
    }

    public static boolean dfs(State[] state, int node, Map<Integer, List<Integer>> adj, Stack<Integer> topoSort) {
        state[node] = State.VISITING; // Mark node as visiting

        for (int neighbor : adj.get(node)) {
            if (state[neighbor] == State.NOT_VISITED) {
                if (!dfs(state, neighbor, adj, topoSort)) {
                    return false; // Cycle detected
                }
            } else if (state[neighbor] == State.VISITING) {
                return false; // Back edge found, cycle detected
            }
        }

        state[node] = State.VISITED; // Mark node as fully processed
        topoSort.push(node); // Add to topological sort stack
        return true;
    }

    public static void main(String[] args) {
        // Test case 1: Possible to finish courses
        int[][] prerequisites1 = {
                {1, 0},
                {0, 2}
        };
        System.out.println("Test case 1: " + canFinish(3, prerequisites1)); // Expected: true

        // Test case 2: Impossible due to cycle
        int[][] prerequisites2 = {
                {1, 0},
                {0, 1}
        };
        System.out.println("Test case 2: " + canFinish(2, prerequisites2)); // Expected: false

        // Test case 3: No prerequisites
        System.out.println("Test case 3: " + canFinish(2, new int[0][])); // Expected: true
    }
}