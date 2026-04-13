//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class Solution {
    public int maxProfit(int[] p) {
        int min=p[0], profit = 0;
        for(int i=1;i<p.length;i++){
            if(p[i]-min > profit)profit = p[i]-min;
            if(p[i]<min)min=p[i];
        }
        return profit;
    }
}
