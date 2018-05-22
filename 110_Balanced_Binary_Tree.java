/**
110. Balanced Binary Tree
https://leetcode.com/problems/balanced-binary-tree
*/
////////////////////////////////////////////////////////////////

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Solution 1. DFS
// Time Complexity: O(n)
// Be careful, there are two different definition for the balanced binary tree.
// For this definition, we need to check two subtrees' maxDepth difference.
class Solution {
    private boolean res = true;
    public boolean isBalanced(TreeNode root) {
        height(root);
        return res;
    }

    public int height(TreeNode root) {
        if(root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);

        if(Math.abs(l - r) > 1) res = false;
        return Math.max(l, r) + 1;
    }
}