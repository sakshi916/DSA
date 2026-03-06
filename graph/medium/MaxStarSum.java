/**
https://leetcode.com/problems/maximum-star-sum-of-a-graph/

There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.

You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
The star sum is the sum of the values of all the nodes present in the star graph.

Given an integer k, return the maximum star sum of a star graph containing at most k edges.

 */

class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n=vals.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++)adj.add(new ArrayList<>());
        for(int[] it:edges){
            adj.get(it[0]).add(it[1]);
            adj.get(it[1]).add(it[0]);
        }

        int maxi= Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            List<Integer> temp = new ArrayList<>(adj.get(i));
            temp.sort((a,b) -> vals[b] - vals[a]);

            int sum=vals[i];
            int num=k;
            for(int j=0;j<temp.size() && num > 0;j++){
                sum += vals[temp.get(j)] >=0 ? vals[temp.get(j)] : 0;
                num --;
            }
            maxi = Math.max(maxi,sum);
        }
        return maxi;

    }
}
