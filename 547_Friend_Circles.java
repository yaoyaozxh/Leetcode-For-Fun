/**
547. Friend Circles
https://leetcode.com/problems/friend-circles/
*/
////////////////////////////////////////////////////////////////


// The problem is to calculate the number of connected components in the undirected graph.
// Time Complexity: O(V+E) = O(n^2)

// Solution 1. DFS
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        if(n == 0) return 0;

        int[] visited = new int[n];
        int ans = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(i, M, visited);
                ans++;
            }
        }
        return ans;
    }

    public void dfs(int i, int[][] M, int[] visited) {
        for(int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(j, M, visited);
            }
        }
    }
}

// Solution 2. BFS
class Solution {
    public int findCircleNum(int[][] M) {
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        boolean[] visited = new boolean[M.length];
        for(int i = 0; i < M.length; i++) {
            if(visited[i]) continue;
            queue.offer(i);
            while(!queue.isEmpty()) {
                int j = queue.poll();
                visited[j] = true;
                for(int k = 0; k < M.length; k++)
                    if(M[j][k] == 1 && !visited[k])
                        queue.offer(k);
            }
            cnt++;
        }
        return cnt;
    }
}