package graph;

import java.util.*;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Your solution here
        return false;
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