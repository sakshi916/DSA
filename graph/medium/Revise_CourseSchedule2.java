/**
https://leetcode.com/problems/course-schedule-ii/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 */
class Solution {
    public int[] findOrder(int n, int[][] edges) {
        List<List<Integer>> g=new ArrayList<>();
        int[] d=new int[n];
        for(int i=0;i<n;i++)g.add(new ArrayList<>());
        for(int[] edge:edges){
            g.get(edge[1]).add(edge[0]);
            d[edge[0]]++;
        }

        Queue<Integer> q= new LinkedList<>();
        for(int i=0;i<n;i++){
            if(d[i] == 0)q.offer(i);
        }
        int[] order=new int[n];
        int ind=0;
        while(!q.isEmpty()){
            int curr=q.poll();
            order[ind++] = curr;
            for(int j:g.get(curr)){
                d[j]--;
                if(d[j] == 0)q.offer(j);
            }
        }
        return ind == n ? order : new int[0];

    }
}
