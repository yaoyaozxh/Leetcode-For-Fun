/**
417. Pacific Atlantic Water Flow
https://leetcode.com/problems/pacific-atlantic-water-flow/
*/
////////////////////////////////////////////////////////////////


// Solution 1: DFS
// Time Complexity: O(mn)
// Naive DFS is to search each cell checking whether current path can reach borders.
// Here is a little trick that we search from borders to find where the water can flood.
public class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return Collections.emptyList();
        }

        int m = matrix.length, n = matrix[0].length;

        boolean[][] p_visited = new boolean[m][n];
        boolean[][] a_visited = new boolean[m][n];

        List<int[]> res = new LinkedList<>();

        // also need to init visited array
        for(int i = 0; i < m; i++) {
            p_visited[i][0] = true;
            a_visited[i][n-1] = true;
            dfs(i, 0, p_visited, matrix);
            dfs(i, n - 1, a_visited, matrix);
        }

        for(int j = 0; j < n; j++){
            p_visited[0][j] = true;
            a_visited[m-1][j] = true;
            dfs(0, j, p_visited, matrix);
            dfs(m - 1, j, a_visited, matrix);
        }

        for(int i = 0; i < m; i++) 
            for(int j = 0; j < n; j++) 
                if(p_visited[i][j] && a_visited[i][j]) 
                    res.add(new int[]{i, j});
        return res;
    }
    
    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public void dfs(int i, int j, boolean[][] visited, int[][] matrix) {
        for(int[] d : dir) {
            int x = i + d[0], y = j + d[1];
            if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] < matrix[i][j])
                continue;
            visited[x][y] = true;
            dfs(x, y, visited, matrix);
        }
    }
}