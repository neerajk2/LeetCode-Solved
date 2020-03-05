// Question Link : https://leetcode.com/problems/find-pivot-index/


class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        if (nums.length == 0) return -1;
        for (int i : nums) totalSum += i;
        if (nums[0]==totalSum) return 0;
        int currSum = 0;
        for(int i=0; i<nums.length-1; i++) 
        {
            currSum += nums[i];
            if(currSum == totalSum - currSum - nums[i+1]) return i+1;
        }
        if (nums[nums.length-1]==totalSum) return nums.length-1;
        return -1;
    }
}
