package graph;

public class RottenFruit {

    public static int rottingFruits(int[][] grid) {
        return 0;
    }

    public static void main(String[] args) {
        // Test case 1: Simple case (all fruits rot)
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Test case 1: " + rottingFruits(grid1)); // Expected: 4

        // Test case 2: No fresh fruits
        int[][] grid2 = {
                {0, 2, 0},
                {2, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Test case 2: " + rottingFruits(grid2)); // Expected: 0

        // Test case 3: Impossible case (some fresh fruits can't rot)
        int[][] grid3 = {
                {2, 1, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Test case 3: " + rottingFruits(grid3)); // Expected: -1

        // Test case 4: All fresh fruits
        int[][] grid4 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Test case 4: " + rottingFruits(grid4)); // Expected: -1

        // Test case 5: Already rotten fruits
        int[][] grid5 = {
                {2, 2, 2},
                {2, 2, 2},
                {2, 2, 2}
        };
        System.out.println("Test case 5: " + rottingFruits(grid5)); // Expected: 0

        // Test case 6: Empty grid
        int[][] grid6 = {};
        System.out.println("Test case 6: " + rottingFruits(grid6)); // Expected: -1

        // Test case 7: Single fresh fruit
        int[][] grid7 = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Test case 7: " + rottingFruits(grid7)); // Expected: -1

        // Test case 8: Single row with rotting
        int[][] grid8 = {
                {2, 1, 1, 0, 1}
        };
        System.out.println("Test case 8: " + rottingFruits(grid8)); // Expected: 2
    }
}