//https://leetcode.com/problems/target-sum/description/
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer,Integer> c=new HashMap<>();
        c.put(0,1);
        for(int n:nums){
            Map<Integer,Integer> t=new HashMap<>();
            for(Map.Entry<Integer,Integer> entry:c.entrySet()){
                int tot=entry.getKey();
                int cnt = entry.getValue();
                t.put(tot+n, t.getOrDefault(tot+n,0)+cnt);
                t.put(tot-n, t.getOrDefault(tot-n,0)+cnt);
            }
            c=t;
        }
        return c.getOrDefault(target,0);
    }
}
