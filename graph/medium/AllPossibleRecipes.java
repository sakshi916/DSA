//https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
class Solution {
    public List<String> findAllRecipes(String[] r, List<List<String>> ing, String[] s) {
        Map<String, Set<String>> ir= new HashMap<>();
        Map<String, Integer> d = new HashMap<>();
        for(int i=0;i < r.length;i++){
            for(String ingred : ing.get(i)){
                ir.computeIfAbsent(ingred, str->new HashSet<>()).add(r[i]);
            }
            d.put(r[i],ing.get(i).size());
        }
        List<String> ans = new ArrayList<>();
        Queue<String> a= Stream.of(s).collect(Collectors.toCollection(LinkedList::new));
        while(!a.isEmpty()){
            String ingred = a.poll();
            if(ir.containsKey(ingred)){
                for(String rcp: ir.remove(ingred)){
                    if(d.merge(rcp,-1,Integer::sum)==0){
                        a.offer(rcp);
                        ans.add(rcp);
                    }
                }
            }
        }
        return ans;
    }
}
