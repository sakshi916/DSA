/**
https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

A connected trio is a set of three nodes where there is an edge between every pair of them.

The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.

 
 */

class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        //create adjacency list graph 
        for(int[] edge : edges) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[1]).add(edge[0]);
        }
        int mindegree = Integer.MAX_VALUE;
        for(int[] edge : edges) {
            
            int first = edge[0];
            int second = edge[1];
            
            for(int i = second + 1; i <= n; i++) {
                int third = i;
                //check if for given pair first and second, does there exist another node which makes trio
                if(graph.containsKey(third) && graph.get(third).contains(first) && graph.get(third).contains(second)) {
                    
                    //if yes, get their combined number of edges minues their own interconnected edges(2 each = 6)
                    int current = graph.get(first).size() + graph.get(second).size() + graph.get(third).size() - 6;
                    mindegree = Math.min(mindegree, current);
                }
                
            }
            
        } 
        return mindegree == Integer.MAX_VALUE ? - 1 : mindegree;                
    }
}
