
/**
https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/

There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.

You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.

Return true if it is possible to make the degree of each node in the graph even, otherwise return false.

The degree of a node is the number of edges connected to it.
 */

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        // number of edges of each node
        int[] noe=new int[n+1];
        
        // make a graph
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        for(List<Integer> c:edges){
            int x=c.get(0);
            int y=c.get(1);
            graph.get(x).add(y);
            graph.get(y).add(x);
            noe[x]++;
            noe[y]++;
        }
        
        // nodes with number of odd edges will be in the list
        List<Integer> list=new ArrayList<>();
        
        for(int i=0;i<noe.length;i++){ 
            if(noe[i]%2==1){
                list.add(i);
            }
        }
        
        int odd=list.size();   
        
        //no odds
        if(odd==0) return true;
        else if(odd>4||odd==1||odd==3) return false; // we can't get ans with these conditions because edges will connect 2 nodes
        else if(odd==4){ // if odd is 4 then we have to try out every possible combination
            int node1=list.get(0);
            int node2=list.get(1);
            int node3=list.get(2);
            int node4=list.get(3);
            if(checkIfLegal(graph,node1,node2) && checkIfLegal(graph,node3,node4)) return true;
            if(checkIfLegal(graph,node1,node3) && checkIfLegal(graph,node2,node4)) return true;
            if(checkIfLegal(graph,node1,node4) && checkIfLegal(graph,node2,node3)) return true;
        }else if(odd==2){ // if odd is 2 there are 2 conditions either join the 2 nodes with odd edges or join one node with even edge with 2 nodes with odd edge
            int x=list.get(0);
            int y=list.get(1);
            if(checkIfLegal(graph,y,x)) return true;
            for(int i=1;i<noe.length;i++){
                if(i==x||i==y) continue;
                if(checkIfLegal(graph,i,x) && checkIfLegal(graph,i,y)) return true;
            }
        }
        return false;
    }
    
    // will check if there is not a repeated edge or self loop
    public boolean checkIfLegal(List<List<Integer>> graph,int x,int y){
        for(int val:graph.get(x)) if(val==y) return false;
        return true;
    }
    
}
