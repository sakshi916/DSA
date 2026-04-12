//https://leetcode.com/problems/redundant-connection/description/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<= n;i++)
            adj.add(new ArrayList<>());

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int[] vis = new int[n+1];
            if(hasPath(u,v,adj,vis))
                return edge;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return new int[0];
    }

    boolean hasPath(int src, int dest, List<List<Integer>> adj, int[] vis){
        if(src == dest) return true;
        vis[src]=1;
        for(int n: adj.get(src))
            if(vis[n] == 0)
                if(hasPath(n, dest, adj, vis))
                    return true;

        return false;
    }
}
