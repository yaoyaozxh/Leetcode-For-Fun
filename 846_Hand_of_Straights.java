/**
846. Hand of Straights
https://leetcode.com/problems/hand-of-straights
*/
////////////////////////////////////////////////////////////////

// Solution 1.
// Time Complexity: O(nlgn)
// At first I thought about sorting and hashmap, but do remember there is TreeMap
// to make it easier!
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length % W != 0) return false;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for(int card: hand) m.put(card, m.getOrDefault(card, 0) + 1);
        
        while(!m.isEmpty()) {
            int cur = m.firstKey();
            for(int i = 0; i < W; i++) {
                if(!m.containsKey(cur+i)) return false;
                int remain = m.get(cur+i);
                if(remain == 1) m.remove(cur+i);
                else m.put(cur+i, remain - 1);
            }
        }
        return true;
    }
}