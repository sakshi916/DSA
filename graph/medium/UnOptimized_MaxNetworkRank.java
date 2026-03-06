/**
https://leetcode.com/problems/maximal-network-rank/
There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 
 */
class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] d = new int[n];   // degrees 
        Set<String> rs=new HashSet<>();
        //calculate degrees of every node and also maintain the pair connections
        for(int[] r:roads){
            d[r[0]]++;
            d[r[1]]++;
            rs.add(r[0]+","+r[1]);
            rs.add(r[1]+","+r[0]);
        }

        int r=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int h=d[i]+d[j];

                //if the pair is connected, reduce rank by 1
                if(rs.contains(i+","+j))
                    h--;
                r=Math.max(r,h);
            }
            
        }
        return r;
    }
}
