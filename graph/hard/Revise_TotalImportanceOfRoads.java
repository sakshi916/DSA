/**
https://leetcode.com/problems/maximum-total-importance-of-roads/
You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.

You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

You need to assign each city with an integer value from 1 to n, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.

Return the maximum total importance of all roads possible after assigning the values optimally.
 */
class Solution {
    public long maximumImportance(int n, int[][] roads) {
        int[] d=new int[n];
        for(int i=0;i<roads.length;i++){
            d[roads[i][0]]++;
            d[roads[i][1]]++;
        }
        Integer[] c=new Integer[n];
        for(int i=0;i<n;i++){
            c[i]=i;
        }
        //sort c based on d
        Arrays.sort(c,new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return Integer.compare(d[b],d[a]);
            }
        });

        long ans=0;
        for(int i=0;i<n;i++){
            ans+=(long)(n-i)*d[c[i]];
        }
        return ans;
    }
}
