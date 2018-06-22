/**
17. Letter Combinations of a Phone Number
https://leetcode.com/problems/letter-combinations-of-a-phone-number
*/
////////////////////////////////////////////////////////////////

// Solution 1. Backtracking DFS
// Time Complexity: ~O(3^n)

class Solution {
    private static String[] dict = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return Collections.emptyList();
        List<String> res = new ArrayList<>();

        dfs(digits, res, new StringBuilder(), 0);
        return res;
    }

    private void dfs(String digits, List<String> res, StringBuilder sb, int k) {
        if(k == digits.length()) { res.add(sb.toString()); return; }
        String s = dict[digits.charAt(k) - '0' - 2];
        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            dfs(digits, res, sb, k + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}