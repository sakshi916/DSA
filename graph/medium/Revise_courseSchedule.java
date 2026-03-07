/**
https://leetcode.com/problems/course-schedule/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.
 */
class Solution {
    public boolean canFinish(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: edges){
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] vis=new boolean[n];
        boolean[] path=new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i] && dfs(i,adj, vis,path))return false;
        }
        return true;
    }

    private boolean dfs(int node,List<List<Integer>> adj, boolean[] vis, boolean[] path){
        vis[node]=true;
        for(int next:adj.get(node)){
            if (!vis[next] && dfs(next, adj, vis, path)) return true;
            else if(path[next]) return true;
        }
        path[node] = false;
        return false;
    }
}
