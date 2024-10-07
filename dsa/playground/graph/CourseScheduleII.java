package graph;

import java.util.*;

public class CourseScheduleII {
    enum State {
        NOT_VISITED, VISITING, VISITED
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
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
                    return new int[0]; // Cycle detected
                }
            }
        }

        // Print topological sort order
        System.out.println("Topological Sort: " + topoSort);
        return stackToArray(topoSort);
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

    public static int[] stackToArray(Stack<Integer> stack) {
        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case 1: General case
        int[][] prerequisites1 = {
            {1, 0},
            {2, 0},
            {3, 1},
            {3, 2}
        };
        System.out.println("Test case 1: " + Arrays.toString(findOrder(4, prerequisites1))); // Expected: [0, 2, 1, 3]

        // Test case 2: No prerequisites
        System.out.println("Test case 2: " + Arrays.toString(findOrder(2, new int[0][]))); // Expected: [0, 1]

        // Test case 3: Impossible case
        int[][] prerequisites2 = {
            {1, 0},
            {0, 1}
        };
        System.out.println("Test case 3: " + Arrays.toString(findOrder(2, prerequisites2))); // Expected: []
    }
}