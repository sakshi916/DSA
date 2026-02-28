/**
https://leetcode.com/problems/keys-and-rooms/description/
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
  **/
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] b= new boolean[rooms.size()];
        b[0]=true;
        Stack<Integer> s=new Stack<>();
        s.push(0);
        while(!s.isEmpty()){
            int curr=s.pop();
            for(Integer i:rooms.get(curr)){
                if(!b[i]){ //To avoid infinite loop in case multiple rooms have duplicate keys
                s.push(i);
                b[i]=true;
                }
            }
        }
     for(int i=0;i<b.length;i++){
        if(!b[i])return false;
     }
     return true;
    }
   
}
