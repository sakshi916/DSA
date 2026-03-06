/**
https://leetcode.com/problems/detonate-the-maximum-bombs/description/

You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
**/
class Solution {
    public int maximumDetonation(int[][] b) {
        Map<Integer,List<Integer>> g=new HashMap<>();
        int l=b.length;
for(int i=0;i<l;i++)
            g.put(i,new ArrayList<>());
        //create graph
        for(int i=0;i<l;i++){
            int x1=b[i][0];
            int y1=b[i][1];
            int r1=b[i][2];

            for(int j=0;j<l;j++){
                if(i==j)continue;
                int x2=b[j][0];
                int y2=b[j][1];
                 long dx = x2 - x1, dy = y2 - y1;
                long d = (dx * dx) + (dy * dy);
                if(d<=(long)r1*r1){
                    
                   g.get(i).add(j);
                }

            }
        }
        int ans=1;
        //for every node, get their max covered nodes
        for(int i=0;i<l;i++){
            ans=Math.max(ans,bfs(i,g,l));
        }
        return ans;
    }

    private int bfs(int i,Map<Integer,List<Integer>> g, int l){
        boolean[] visited=new boolean[l];
        Queue<Integer> q=  new LinkedList<>();
        visited[i]=true;
        q.offer(i);
        int cnt=1;
        while(!q.isEmpty()){
            int curr=q.poll();
        
            for(int j:g.get(curr)){
                if(!visited[j]){
                    q.offer(j);
                    visited[j]=true;
                    cnt++;
                }
            }
            
        }
        return cnt;
    }
}
