/**
576. Out of Boundary Paths
https://leetcode.com/problems/out-of-boundary-paths
*/
////////////////////////////////////////////////////////////////

// Solution 1. DFS
// Time Complexity: O(m*n*N)
// Recursion with memorization
class Solution {
    int M = 1000000007;
    int[][][] memo;
    int m, n;

    public int findPaths(int m, int n, int N, int i, int j) {
        this.m = m; this.n = n; this.memo = new int[m][n][N+1];

        for(int[][] arr : memo)
            for(int[] subarr : arr)
                Arrays.fill(subarr, -1);
        return dfs(i,j,N);
    }

    public int dfs(int i, int j, int N) {
        if(i == m || j == n || i < 0 || j < 0) return 1; // out of boundary
        if(N == 0) return 0;
        if(memo[i][j][N] >= 0) return memo[i][j][N]; // hit memo
        memo[i][j][N] = ((dfs(i-1, j, N-1) + 
                        dfs(i+1, j, N-1)) % M +
                        (dfs(i, j-1, N-1) + 
                        dfs(i, j+1, N-1)) % M) % M;
        return memo[i][j][N];
    }
}


// Solution 2. DP
// Time Complexity: O(m*n*N)
// Space Complexity: O(m*n)
// dp stores the number of ways that can be reached by making use of N moves,
// dp[N][i][j] = dp[N-1][i-1][j] + dp[N-1][i+1][j] + dp[N-1][i][j-1] + dp[N-1][i][j+1]
// use a tmp[m][n] to store dp[N-1][][]
public class Solution {
    public int findPaths(int m, int n, int N, int x, int y) {
        int M = 1000000007;
        int[][] dp = new int[m][n];
        dp[x][y] = 1;
        int cnt = 0;
        for (int nn = 1; nn <= N; nn++) {
            int[][] tmp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == m - 1)
                        cnt = (cnt + dp[i][j]) % M;
                    if (j == n - 1)
                        cnt = (cnt + dp[i][j]) % M;
                    if (i == 0)
                        cnt = (cnt + dp[i][j]) % M;
                    if (j == 0)
                        cnt = (cnt + dp[i][j]) % M;
                    tmp[i][j] = (((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M 
                        + ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M) % M;
                }
            }
            dp = tmp;
        }
        return cnt;
    }
}