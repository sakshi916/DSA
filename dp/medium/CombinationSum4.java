//https://leetcode.com/problems/combination-sum-iv/
class Solution {
    public int combinationSum4(int[] nums, int target) {
        Integer[] m=new Integer[target+1];
        return helper(nums,target,m);
    }
    int helper(int[] n, int t, Integer[] m){
        if(t==0)return 1;
        if(t<0)return 0;
        if(m[t] != null)return m[t];
        int cnt = 0;
        for(int num:n)
            cnt+=helper(n,t-num,m);
        m[t] = cnt;
        return cnt;
    }
}
