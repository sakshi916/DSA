/**
https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/
There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution
 */

class Solution {
    Map<Integer, List<Integer>> gg;
    Map<Integer, List<Integer>> gi;

    int[] gd;
    int[] id;

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        gg = new HashMap<>();
        gi=new HashMap<>();

        for(int i=0;i<group.length;i++)
            if(group[i] == -1) group[i] = m++;
        for(int i=0;i<m;i++){
            gg.put(i,new ArrayList<>());
        }
        for(int i=0;i<n;i++)
            gi.put(i, new ArrayList<>());
        gd=new int[m];
        id=new int[n];

        buildGraphOfGroups(group, beforeItems, n);
        buildGraphOfItems(beforeItems, n);

        List<Integer> gL= topologicalSortUtil(gg,gd,m);
        List<Integer> iL = topologicalSortUtil(gi, id, n);

        if(gL.size() == 0 || iL.size() == 0) return new int[0];

        Map<Integer, List<Integer>> l=new HashMap<>();

        for(Integer item: iL)
            l.computeIfAbsent(group[item], x -> new ArrayList<>()).add(item);

        int[] ans = new int[n];
        int idx=0;
        for(Integer grp:gL){
            List<Integer> items = l.getOrDefault(grp, new ArrayList<>());
            for(Integer item:items)
                ans[idx++] = item;
        }
        return ans;
    }

    void buildGraphOfGroups(int[] group, List<List<Integer>> beforeitems, int n){
        for(int i=0; i<group.length;i++){
            int t=group[i];
            List<Integer> ff = beforeitems.get(i);
            for(int f:ff){
                int fg=group[f];
                if(fg != t){
                    gg.computeIfAbsent(fg, x-> new ArrayList<>()).add(t);
                    gd[t]++;
                }
            }
        }
    }

    void buildGraphOfItems(List<List<Integer>> beforeItems, int n){
        for(int i=0;i<n;i++){
            List<Integer> items = beforeItems.get(i);
            for(Integer item : items){
                gi.computeIfAbsent(item, x-> new ArrayList<>()).add(i);
                id[i]++;
            }
        }
    }

    List<Integer> topologicalSortUtil(Map<Integer, List<Integer>> g, int[] d, int n){
        List<Integer> l=new ArrayList<>();
        Queue<Integer> q= new LinkedList<>();
        for(int k:g.keySet()){
            if(d[k]==0)
                q.add(k);
        }

        while(!q.isEmpty()){
            int node=q.poll();
            n--;
            l.add(node);
            for(int neigh:g.get(node)){
                d[neigh]--;
                if(d[neigh] == 0)
                    q.offer(neigh);
            }
        }
        return n==0 ? l : new ArrayList<>();
    }
}
