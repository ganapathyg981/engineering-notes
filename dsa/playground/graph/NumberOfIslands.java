package graph;

public class NumberOfIslands {
    public static void main(String[] args) {

        // Test case 1: Basic case (one island)
        char[][] grid1 = {
                {'1', '1', '0', '0'},
                {'1', '0', '0', '0'},
                {'0', '0', '0', '1'},
                {'0', '0', '0', '1'}
        };
        System.out.println("Number of islands in grid1: " + numIslands(grid1)); // Expected: 2

        // Test case 2: Multiple islands
        char[][] grid2 = {
                {'1', '1', '0', '0', '1'},
                {'1', '0', '0', '1', '1'},
                {'0', '0', '1', '0', '0'},
                {'1', '1', '0', '1', '0'}
        };
        System.out.println("Number of islands in grid2: " + numIslands(grid2)); // Expected: 5

        // Test case 3: All water
        char[][] grid3 = {
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'}
        };
        System.out.println("Number of islands in grid3: " + numIslands(grid3)); // Expected: 0

        // Test case 4: All land (one large island)
        char[][] grid4 = {
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'}
        };
        System.out.println("Number of islands in grid4: " + numIslands(grid4)); // Expected: 1

        // Test case 5: Single row (one island)
        char[][] grid5 = {
                {'1', '0', '1', '0', '1'}
        };
        System.out.println("Number of islands in grid5: " + numIslands(grid5)); // Expected: 3

        // Test case 6: Single column (two islands)
        char[][] grid6 = {
                {'1'},
                {'0'},
                {'1'},
                {'0'}
        };
        System.out.println("Number of islands in grid6: " + numIslands(grid6)); // Expected: 2

        // Test case 7: Empty grid (edge case)
        char[][] grid7 = new char[][]{};
        System.out.println("Number of islands in grid7: " + numIslands(grid7)); // Expected: 0

        // Test case 8: Mixed land and water
        char[][] grid8 = {
                {'1', '0', '1'},
                {'0', '1', '0'},
                {'1', '0', '1'}
        };
        System.out.println("Number of islands in grid8: " + numIslands(grid8)); // Expected: 5
    }

    private static String numIslands(char[][] grid) {
        return null;
    }
}