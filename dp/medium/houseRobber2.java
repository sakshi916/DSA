//https://leetcode.com/problems/house-robber-ii/description/
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1)return nums[0];
        return Math.max(getMax(nums, 0 ,nums.length-2),getMax(nums,1,nums.length-1));
    }
    int getMax(int[] n, int s, int e){
        int prev=0,max=0;
        for(int i=s;i<=e;i++){
            int t=Math.max(max, prev+n[i]);
            prev=max;
            max=t;
        }
        return max;
    }
}
