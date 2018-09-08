/**
10. Regular Expression Matching
https://leetcode.com/problems/regular-expression-matching/description/
*/
////////////////////////////////////////////////////////////////

// Solution 1: DP
// dp[i][j] denotes whether s[0...i-1] matches p[0...j-1]
// dp[i][j] = dp[i-1][j-1] && (s[i-1] == p[j-1] || p[j-1] == ".") when p[j-1] != "*"
//          = dp[i][j-2] when p[j-1] == "*"
//          = dp[i-1][j] && (s[i-1] == p[j-2] || p[j-2] == ".") when p[j-1] == "*"
// dp[0][0] = True, dp[0][1] = False,
// dp[0][j] = True if .* a* a*b*
// dp[i][0] = False
// Time Complexity: O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(); int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        dp[0][0] = true;
        for(int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        for(int j = 1; j <= n; j++) {
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(p.charAt(j-1) != '*') {
                    dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.');
                } else {
                    dp[i][j] = dp[i][j-2] || (dp[i-1][j] && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'));
                }
            }
        }
        return dp[m][n];
    }
}

