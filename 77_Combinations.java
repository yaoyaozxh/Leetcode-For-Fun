/**
77. Combinations
https://leetcode.com/problems/combinations
*/
////////////////////////////////////////////////////////////////

// Solution 1. DFS
// Time Complexity: ~O(n^k)

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), 1, n, k);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> path, int start, int n, int k) {
        if(k == 0) { 
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = start; i <= n; i++) {
            path.add(i);
            dfs(res, path, i + 1, n, k - 1);
            path.remove(path.size() - 1);
        }
    }
}