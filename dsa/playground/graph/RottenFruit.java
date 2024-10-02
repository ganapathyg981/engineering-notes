package graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Rotting Fruit
You are given a 2-D matrix grid. Each cell can have one of three possible values:

0 representing an empty cell
1 representing a fresh fruit
2 representing a rotten fruit
Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.

Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is impossible within the grid, return -1.


 */
public class RottenFruit {
   static int[][] moveOffset = {{0,1}, {0,-1},{1,0},{-1,0}};

    public static int rottingFruits(int[][] grid) {
        Queue<int []> queue = new LinkedList(); //TODO HOLD INDICES
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int cell = grid[i][j];
                if (cell == 2) {
                    queue.add(new int[]{i, j});
                }
                if (cell == 1) {
                    fresh++;
                }
            }
        }
        if(fresh== 0) {
            return 0;
        }
        if(queue.isEmpty()) {
            return -1;
        }
        int time = 0;
        while (!queue.isEmpty() && fresh>0) {
            int size = queue.size();
            for(int i = 0; i< size; i++) {
                int[] currentRotten = queue.poll();
                int rottenRow = currentRotten[0];
                int rottenCol = currentRotten[1];
                for (int[] offset: moveOffset) {
                    int row = rottenRow+offset[0];
                    int col = rottenCol+offset[1];
                    if(row>=0 && col>=0 && row<grid.length && col<grid[0].length && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        fresh--;
                        queue.add(new int[]{row, col});
                    }
                }
            }
            time ++;
        }
        if (fresh==0) {
            return time;
        } else {
            return -1;
        }
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
                {2,1,1},
                {1,1,1},
                {0,1,2}
        };
        System.out.println("Test case 8: " + rottingFruits(grid8)); // Expected: 2


    }
}