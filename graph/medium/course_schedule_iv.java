/**
https://leetcode.com/problems/course-schedule-iv/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.
 */
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prereq, int[][] queries) {
        boolean[][] conns= new boolean[n][n];
        for(int[] p:prereq)
            conns[p[0]][p[1]] = true;
        for(int k=0;k<n;k++)
        for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            conns[i][j]=conns[i][j] || conns[i][k] && conns[k][j];
        
        List<Boolean> ans = new ArrayList<>();
        for(int[] q:queries)
            ans.add(conns[q[0]][q[1]]);
        return ans;
    }
}
