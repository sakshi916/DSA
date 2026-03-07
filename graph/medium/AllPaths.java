/**
https://leetcode.com/problems/all-paths-from-source-to-target/
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0,graph,res, list);
        return res;
    }

    void dfs(int n, int[][] g, List<List<Integer>> res, List<Integer> list){
        if(n==g.length-1){
            res.add(new ArrayList<>(list));
        }
        //backtracking for all nodes starting from 0
        for(int nbr: g[n]){
            list.add(nbr);
            dfs(nbr, g, res, list);
            list.remove(list.size()-1);
        }
    }
}
