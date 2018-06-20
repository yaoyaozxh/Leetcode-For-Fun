/**
688. Knight Probability in Chessboard
https://leetcode.com/problems/knight-probability-in-chessboard/
*/
////////////////////////////////////////////////////////////////

// Solution 1. DFS
// Time Complexity: O(N*N*K)
// Recursion with memorization. Think from end to start, top down.
// memo[i][j][k] means the probability that knight is still on the board after k moves starting from i,j.
class Solution {
    int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public double knightProbability(int N, int K, int r, int c) {
        double[][][] memo = new double[N][N][K+1];

        return dfs(N,K,r,c,memo);
    }

    private double dfs(int N, int k, int i, int j, double[][][] memo) {
        if(i < 0 || i >= N || j < 0 || j >= N) return 0;
        if(k <= 0) return 1.0;
        if(memo[i][j][k] > 0.0) return memo[i][j][k];
        double ans = 0.0;
        for(int d = 0; d < 8; d++) ans += dfs(N, k-1, i+dx[d], j+dy[d], memo);
        memo[i][j][k] = ans / 8.0;
        return memo[i][j][k];
    }
}

// Solution 2. DP
// Time Complexity: O(N*N*K)
// Think from start to end, bottom up, step by step.
// dp[k][i][j] = 1/8 * (dp[k-1][i-2][j-1] + ...)
// dp[k][i][j] stores the probability that can be i,j reached by making use of k moves.
// We can use a tmp[][] to decrease the space complexity to O(N*N)
class Solution {
    int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        dp[r][c] = 1.0;
        for(int k = 1; k <= K; k++) {
            double[][] tmp = new double[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    for (int d = 0; d < 8; d++) {
                        int x = i + dx[d];
                        int y = j + dy[d];
                        if (x >= 0 && x < N && y >= 0 && y < N) {
                            tmp[i][j] += dp[x][y];
                        }
                    }
                    tmp[i][j] /= 8.0; 
                }
            }
            dp = tmp;
        }

        double ans = 0.0;
        for (double[] row : dp) {
            for (double x : row) ans += x;
        }

        return ans;
    }
}