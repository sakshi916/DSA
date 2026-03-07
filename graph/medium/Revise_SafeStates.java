/**
https://leetcode.com/problems/find-eventual-safe-states/
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 */
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n= graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i= 0; i< n; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < graph[i].length; j++){
                list.add(graph[i][j]);
            }
            adj.add(list);
        }

        boolean[] vis= new boolean[n];
        boolean[] r= new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(adj,i,vis,r);
            }
        }
        
        List<Integer> res= new ArrayList<>();
        for(int i=0;i<r.length;i++){
            if(!r[i])res.add(i);
        }
        return res;
    }

    boolean dfs(List<List<Integer>> adj, int n,boolean[] vis, boolean[] r){
        vis[n] = true;
        r[n] = true;
        for(int x: adj.get(n)){
            if(!vis[x] && dfs(adj,x,vis,r))
                return true;
            else if(r[x])
                return true;
        }
        r[n]=false;
        return false;
    }
}
