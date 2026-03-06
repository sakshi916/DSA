/**
https://leetcode.com/problems/count-pairs-of-nodes/
You are given an undirected graph defined by an integer n, the number of nodes, and a 2D integer array edges, the edges in the graph, where edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.

Let incident(a, b) be defined as the number of edges that are connected to either node a or b.

The answer to the jth query is the number of pairs of nodes (a, b) that satisfy both of the following conditions:

a < b
incident(a, b) > queries[j]
Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.

Note that there can be multiple edges between the same two nodes.
 */
/**
My initial intuition:
1. create a graph
2. store the vertices in a sorted way
3. run O(n^2) loop to get the combined number of edges for every node with it's higher node and add it to a list
4. sort the above list
5. for every query get number of items having value higher than itself from the above list

 */
class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] ed : edges) {
            degree[--ed[0]] ++;
            degree[--ed[1]] ++;
            if (ed[0] > ed[1]) {
                int temp = ed[0];
                ed[0] = ed[1];
                ed[1] = temp;
            }
			// store the number of edges for each node pair
            cnt.put(ed[0] * 20000 + ed[1], cnt.getOrDefault(ed[0] * 20000 + ed[1], 0) + 1);
        }
        
        int[] sorted = degree.clone();
        Arrays.sort(sorted);
        
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int ans = 0;            
			// using two pointers to find how many potential node pairs
            for (int l = 0, r = sorted.length - 1; l < r;) {
                if (sorted[l] + sorted[r] > queries[i]) {
                    ans += (r - l);
                    r --;
                }
                else {
                    l ++;
                }
                
            }
            
			// remove all shared pairs
            for (int k : cnt.keySet()) {
                int fi = k / 20000;
                int se = k % 20000;
                if (degree[fi] + degree[se] > queries[i] && degree[fi] + degree[se] - cnt.get(k) <= queries[i])
                    ans --;
            }
            
            res[i] = ans;
        }
        return res;
    }
}
