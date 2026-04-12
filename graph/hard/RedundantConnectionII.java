//https://leetcode.com/problems/redundant-connection-ii/description/
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // can1 is before can2 and has higher priority
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] roots = new int[edges.length + 1];
        for (int[] edge : edges) {
            int father = edge[0];
            int child = edge[1];
            if (roots[child] == 0) {
                roots[child] = father;
            } else if (roots[child] != 0) {
                // the child already has father
                // the newest link
                can2 = new int[]{father, child};
                // the older version of link
                can1 = new int[]{roots[child], child};
                // set the current link invalid
                edge[1] = 0;
            }
        }
        
        // reuse the roots matrix, do union find
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        
        for (int[] edge : edges) {
            int father = edge[0];
            int child = edge[1];
            if (child == 0) {
                // current link is not valid
                continue;
            } 
            if (find(roots, father) == child) {
                // there is a cycle
                if (can1[0] == -1) {
                    // candidate not exist
                    return edge;
                } else {
                    // candidate exist
                    return can1;
                }
            }
            // union
            roots[child] = father;
        }
        return can2;
    }
    
    
    private int find(int[] roots, int id) {
        while (id != roots[id]) {
            // path compression, optional
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
    
}
