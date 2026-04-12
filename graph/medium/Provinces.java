//https://leetcode.com/problems/number-of-provinces/description/
class Solution {
    public int findCircleNum(int[][] c) {
        Set<Integer> vis = new HashSet<>();
        int p= 0;
        for(int i = 0; i< c.length;i++)
            if(!vis.contains(i)){
                dfs(i,c,vis);
                p++;
            }
        return p;
    }
    void dfs(int n, int[][] c, Set<Integer> vis){
        vis.add(n);
        for(int cur=0;cur<c[n].length;cur++){
            int conn=c[n][cur];
            if(conn == 1 && !vis.contains(cur))
                dfs(cur,c,vis);
        }
    }
}
