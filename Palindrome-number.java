//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
// url : https://leetcode.com/problems/palindrome-number/

class Solution {
    public boolean isPalindrome(int x) {
        String num = Integer.toString(x);
        int len = num.length();
        for (int i = 0; i<len/2; i++){
            if (num.charAt(i) != num.charAt(len-i-1)) return false;
        }
        return true;
    }
}
