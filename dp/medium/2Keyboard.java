//https://leetcode.com/problems/2-keys-keyboard/
class Solution {
    public int minSteps(int n) {
        if(n == 1)return 0;
        int[] minOp = new int[n+1];
        Arrays.fill(minOp,Integer.MAX_VALUE);
        minOp[1] = 0;

        for(int i=2;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                if(i%j==0){
                    minOp[i]=Math.min(minOp[i],minOp[j]+i/j);
                    if(j != i/j)
                    minOp[i]=Math.min(minOp[i],minOp[i/j]+j);
                }
            }
        }
        return minOp[n];
    }
}
