/**
https://leetcode.com/problems/strange-printer-ii/description/
 */
class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        List<List<Integer>> g= new ArrayList<>();
        int[] d=new int[61];
        for(int i=0;i<=60;i++)g.add(new ArrayList<>());
        for(int i=1;i <=60;i++)search(targetGrid, i, g,d);

        Deque<Integer> zeroes = new ArrayDeque<>();
        HashSet<Integer> seen = new HashSet<>();
        for(int i=0; i< d.length;i++)
            if(d[i]==0)zeroes.add(i);
        while(!zeroes.isEmpty()){
            int cur = zeroes.poll();
            if(!seen.add(cur))continue;
            for(Integer nbh : g.get(cur)){
                d[nbh]--;
                if(d[nbh] == 0)zeroes.add(nbh);
            }
        }
        return seen.size() == 61;    
    }
    void search(int[][] grid, int color,List<List<Integer>> g, int[] d){
        int minX = Integer.MAX_VALUE,minY=Integer.MAX_VALUE,maxX=Integer.MIN_VALUE, maxY=Integer.MIN_VALUE;
        for(int i=0;i<grid.length; i++){
            for(int j=0 ; j< grid[0].length;j++){
                if(grid[i][j] == color){
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        if(minX == Integer.MAX_VALUE)return;
        for(int i =minX;i<=maxX;i++)
            for(int j=minY;j<=maxY;j++){
                if(grid[i][j] != color){
                    g.get(grid[i][j]).add(color);
                    d[color]++;
                }
            }
    }
}
