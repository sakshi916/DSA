//https://leetcode.com/problems/build-a-matrix-with-conditions/description/
class Solution {
    public int[][] buildMatrix(int k, int[][] rc, int[][] cc) {
        List<Integer>[] rg = new ArrayList[k+1];
        for(int i=1; i<rg.length;i++)
            rg[i] = new ArrayList<>();
        for(int[] row:rc){
            rg[row[0]].add(row[1]);
        }
        List<Integer>[] cg = new ArrayList[k+1];
        for(int i=1; i<cg.length;i++)
            cg[i] = new ArrayList<>();
        for(int[] row:cc){
            cg[row[0]].add(row[1]);
        }

        int[] vis=new int[k+1];
        Deque<Integer> q=new LinkedList<>();
        for(int i=1;i<rg.length;i++)
            if(!topSort(rg,i,vis,q))
                return new int[0][0];

        int[] rowIndex = new int[k+1];
        for(int i=0;i<k;i++){
            int n=q.pollLast();
            rowIndex[n] = i;
        }

        vis = new int[k+1];
        q=new LinkedList<>();

        for(int i=1;i<cg.length;i++)
            if(!topSort(cg,i,vis,q))
                return new int[0][0];

        int[] colOrder = new int[k];
        int[] colIndex = new int[k+1];
        for(int i=0;i<k;i++){
            int n=q.pollLast();
            colOrder[i] = n;
            colIndex[n] = i;
        }

        int[][] res = new int[k][k];
        for(int i=1;i<=k;i++)
            res[rowIndex[i]][colIndex[i]] = i;
        return res;
    }

    boolean topSort(List<Integer>[] g, int n, int[] vis, Deque<Integer> q){
        if(vis[n] == 2)
            return false;
        if(vis[n] == 0){
            vis[n]=2;
            for(int child: g[n])
                if(!topSort(g, child, vis, q))
                    return false;
            vis[n] = 1;
            q.add(n);
        }
        return true;
    }
}
