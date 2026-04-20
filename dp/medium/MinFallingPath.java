//https://leetcode.com/problems/minimum-falling-path-sum
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for(int i = 1; i < m; i++) {
            // first column
            grid[i][0] += Math.min(grid[i-1][0], grid[i-1][1]);

            // middle columns
            for(int j = 1; j < n-1; j++) {
                grid[i][j] += Math.min(
                    grid[i-1][j],
                    Math.min(grid[i-1][j-1], grid[i-1][j+1])
                );
            }

            // last column
            grid[i][n-1] += Math.min(grid[i-1][n-1], grid[i-1][n-2]);
        }

        int min = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++) {
            min = Math.min(min, grid[m-1][j]);
        }

        return min;
    }
}
