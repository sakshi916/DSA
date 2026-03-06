/**
https://leetcode.com/problems/get-watched-videos-by-your-friends/
There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain
the list of watched videos and the list of friends respectively for the person with id = i.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, 
the level k of videos are all watched videos by people with the shortest path exactly equal to k with you. Given your id and the level of videos,
return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest. 
**/
class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;

        boolean visited[] = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        visited[id] = true;

        for (int i = 0; i < level && !q.isEmpty(); i++) {
            int s = q.size();

          //At the end of next for loop, q will contain all friends of id at level i
            for (int j = 0; j < s; j++) {
                int curr = q.poll();
                for (int next : friends[curr]) {
                    if(!visited[next]){
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
        //At this point the q holds the friends at given level
        //Now store their videos and frequency

        Map<String,Integer> m=new HashMap<>();
        while(!q.isEmpty()){
            int curr=q.poll();
            for(String video:watchedVideos.get(curr)){
                m.put(video,m.getOrDefault(video,0)+1);
            }
        }
        
        //sort by frequency or alphabetical order
        List<Map.Entry<String,Integer>> l=new ArrayList<>(m.entrySet());
        Collections.sort(l,(a,b)->{
            int v=Integer.compare(a.getValue(),b.getValue());
            return v!=0 ? v:a.getKey().compareTo(b.getKey());
        }) ;
        List<String> results=new ArrayList<>();
        for(Map.Entry<String,Integer> e:l){
            results.add(e.getKey());
        }
        return results;
    }
}
