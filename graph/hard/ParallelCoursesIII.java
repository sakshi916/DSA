/**
https://leetcode.com/problems/parallel-courses-iii/description/
 */
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<n;i++)g.add(new ArrayList<>());
        for(int[] r:relations){
            int prev=r[0] - 1;
            int next = r[1] -1;
            g.get(next).add(prev);
        }
        int[] m = new int[n];
        int res = 0;
        for(int i = 0; i < n; i++)
            res = Math.max(res, calculateTime(i, g, time, m));
        return res;
    }
    int calculateTime(int c, List<List<Integer>> g, int[] time, int[] m){
        if(m[c] != 0)
            return m[c];
        int maxTime = 0;
        for(int p: g.get(c))
            maxTime = Math.max(maxTime, calculateTime(p,g,time,m));

        m[c] = maxTime + time[c];
        return m[c];
    }
}
