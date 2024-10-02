package graph;

import java.util.*;

public class CourseScheduleII {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Your solution here
        return new int[0];
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