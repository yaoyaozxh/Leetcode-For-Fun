/**
415. Add Strings
https://leetcode.com/problems/add-strings/
*/
////////////////////////////////////////////////////////////////

class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0, digit = 0;
        int l1 = num1.length(), l2 = num2.length();
        int i = l1 - 1, j = l2 - 1;
        StringBuilder r = new StringBuilder();
        while(i >= 0 || j >= 0) {
            int sum = carry;
            if(i >= 0) {
                sum += num1.charAt(i) - '0';
            }
            if(j >= 0) {
                sum += num2.charAt(j) - '0';
            }
            digit = sum % 10;
            carry = sum / 10;
            r.append(digit);
            i -= 1; j -= 1;
        }
        if(carry != 0) r.append(carry);
        return r.reverse().toString();
    }
}