//https://leetcode.com/problems/guess-number-higher-or-lower-ii/
class Solution {
    public int getMoneyAmount(int n) {
        int[][] t = new int[n+1][n+1];
        for(int j=2; j<=n; j++)
            for(int i=j-1; i > 0; i--){
                int g = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    int l=k+Math.max(t[i][k-1],t[k+1][j]);
                    g=Math.min(g,l);
                }
                t[i][j] = i+1 == j?i:g;
            }
        return t[1][n];
    }
}
