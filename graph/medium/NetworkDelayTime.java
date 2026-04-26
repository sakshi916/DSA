//https://leetcode.com/problems/network-delay-time/
class Solution {
    static class Pair{
        int dist,node;
        Pair(int u,int v){
            this.dist=v;
            this.node=u;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>>adj=new ArrayList<>();
        for(int i=0;i<=n;i++)adj.add(new ArrayList<>());
        for(int[]row:times){
            int u=row[0];
            int v=row[1];
            int w=row[2];
            adj.get(u).add(new Pair(v,w));
        }
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->a.dist-b.dist);
        int[]distace=new int[n+1];
        Arrays.fill(distace,(int)1e9);
        distace[k]=0;
        pq.add(new Pair(k,0));
        while(!pq.isEmpty()){
            Pair cur=pq.poll();
            int curNode=cur.node;
            int curDistance=cur.dist;
            if(curDistance>distace[curNode])continue;
            for(Pair nbr:adj.get(curNode)){
                int nextnbr= nbr.node;;
                int weight=nbr.dist;
                if(curDistance+weight<distace[nextnbr]){
                    distace[nextnbr]=curDistance+weight;
                    pq.add(new Pair(nextnbr,distace[nextnbr]));
                }
            }
        }
        int ans=0;
        for(int i=1;i<=n;i++){
            if(distace[i]==(int)1e9)return -1;
            ans=Math.max(ans,distace[i]);
        }
        return ans;
    }
}
