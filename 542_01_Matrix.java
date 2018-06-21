/**
542. 01 Matrix
https://leetcode.com/problems/01-matrix
*/
////////////////////////////////////////////////////////////////

// Solution 1. BFS
// We know that we can use BFS to calculate the shortest path to nearest 0, but if we loop every cell
// in the matrix, the complexity is a disaster.
// We need a small trick here. To find the nearest 0, we can start from 0 and use BFS to update all 
// the nearest value for the matrix. Just like an intermediate step of BFS, current level is all 0 cells,
// so we can make sure that each update is the optimal value.

// Time Complexity: O(m * n)
// Since the new cells are added to the queue only if the new distance is smaller than the current value,
// cells are not likely to be added multiple times.

class Solution {
    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] r = new int[m][n];
        Queue<Point> q = new ArrayDeque<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) q.offer(new Point(i, j));
                else r[i][j] = Integer.MAX_VALUE;
            }
        }
        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int[] d: dir) {
                int x = p.x + d[0], y = p.y + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n) {
                    if(r[p.x][p.y] + 1 < r[x][y]) {
                        r[x][y] = r[p.x][p.y] + 1;
                        q.offer(new Point(x, y));
                    }
                }
            }
        }
        return r;
    }
}


// Solution 2: DP
// We can easily know that dp[i][j] = 0 if it's 0 cell, which is a optimal substructure.
// we have four directions to go. If we simply build the dp table from four directions, we
// cannot ensure each time is the optimal value.
// We have a trick here, suppose we can only move right and down and we construct the dp table from top to bottom-left to right.
// Then we do the same thing from bottom to top-right to left. In this way, we ensure each iteration is 
// a optimal substructure which is the requirement of DP algorithm.
// Time Complexity: O(m * n)
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        // Init dp table with maximum value or zero
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = matrix[i][j] == 0 ? 0 : m + n;

        // only move right and down
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                if (j > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }

        // move up and left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i < m - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                if (j < n - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
            }
        }
        return dp;
    }
}