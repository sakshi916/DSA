/**
https://leetcode.com/problems/find-center-of-star-graph/description/
There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.

**/
class Solution {
    public int findCenter(int[][] m) {
        int[] a=new int[m.length+2];
        for(int i=0;i<m.length;i++){
            a[m[i][0]]++;
            a[m[i][1]]++;
        }
        for(int i=1;i<a.length;i++){
            if(a[i]==m.length)return i;}
        return -1;
    }
}
