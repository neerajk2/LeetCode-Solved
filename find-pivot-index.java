// Question Link : https://leetcode.com/problems/find-pivot-index/


class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        if (nums.length == 0) return -1;
        for (int i : nums) total += i;
        if (nums[0]==total) return 0;
        int sum = 0;
        for(int i=0; i<nums.length-1; i++) 
        {
            sum += nums[i];
            if(sum == total - sum - nums[i+1]) return i+1;
        }
        if (nums[nums.length-1]==total) return nums.length-1;
        return -1;
    }
}
