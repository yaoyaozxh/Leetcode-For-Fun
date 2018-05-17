/**
329. Longest Increasing Path in a Matrix
https://leetcode.com/problems/longest-increasing-path-in-a-matrix
*/
////////////////////////////////////////////////////////////////


// Solution 1. DFS with memorization
// Time Complexity: O(mn)

// DFS for each cell, get the longest path.
// Use memo to avoid revisit the same cell.
// Because we need to find strict increasing path, so there is no need 
// for a visited array, because it cannot search back.

class Solution {
    public static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int l = dfs(matrix, i, j, memo);
                max = Math.max(max, l);
            }
        }   
        return max;
    }

    public int dfs(int[][] m, int i, int j, int[][] memo) {
        if(memo[i][j] != 0) return memo[i][j];
        int longest = 1;
        for(int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m.length || y < 0 || y >= m[0].length || m[x][y] <= m[i][j]) continue;
            longest = Math.max(longest, dfs(m, x, y, memo) + 1);
        }
        memo[i][j] = longest;
        return memo[i][j];
    }
}


// Solution 2. BFS and topological sort

// Time Complexity: O(mn)
// We can change the matrix to a DAG and topological sort it.
// If a neighbor's value is greater than itself, there is a directed edge.

class Solution {
    private static class Vertex {
        int x, y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            Vertex v = (Vertex)o;
            return v != null && v.x == x && v.y == y;
        }
    }

    public static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;

        // Construct the DAG
        Map<Vertex, Set<Vertex>> graph = new HashMap<>();
        Map<Vertex, Integer> indegree = new HashMap<>(); // for topological sort
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                Vertex v = new Vertex(i, j);
                graph.computeIfAbsent(v, k -> new HashSet<>());
                for(int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if(x < 0 || y < 0 || x >= m || y >= n || matrix[x][y] <= matrix[i][j]) continue;
                    Vertex next = new Vertex(x, y);
                    graph.get(v).add(next);
                    indegree.put(next, indegree.getOrDefault(next, 0) + 1);
                }
            }
        }
        // Topological sort
        Queue<Vertex> queue = new LinkedList<>();
        // Add those vertice whose indegree is 0
        for(Vertex v : graph.keySet()) {
            if(indegree.getOrDefault(v, 0) == 0) {
                queue.offer(v);
            }
        }
        int l = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            l++; 
            for(int i = 0; i < size; i++) {
                Vertex cur = queue.poll();
                for(Vertex next : graph.getOrDefault(cur, Collections.emptySet())) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return l;
    }
}