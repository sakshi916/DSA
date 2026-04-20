//https://leetcode.com/problems/perfect-squares/
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            int m=dp[i];
            for(int j=1;j*j <= i;++j){
                m=Math.min(m,dp[i-j*j]+1);
            }
            dp[i]=m;
        }
        return dp[n];
    }
}
