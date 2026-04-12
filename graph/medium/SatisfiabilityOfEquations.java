//https://leetcode.com/problems/satisfiability-of-equality-equations/description/
class Solution {
    int[] uf = new int[26];
    public boolean equationsPossible(String[] eq) {
        for(int i=0; i<26;++i)uf[i] = i;
        for(String e:eq){
            if(e.charAt(1)== '=')
                uf[find(e.charAt(0) - 'a')] = find(e.charAt(3)-'a');
        }
        for(String e:eq){
            if(e.charAt(1)=='!' && find(e.charAt(0)-'a') == find(e.charAt(3)-'a'))
                return false;
        }
        return true;
    }
    int find(int x){
        if(x!=uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
}
