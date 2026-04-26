//https://leetcode.com/problems/shortest-cycle-in-a-graph/
class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, Set<Integer>> g = new HashMap<>();

        for (int i = 0; i < n ; i++) {
            g.put(i, new HashSet<>());
        }
        for (int[] e: edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        int inf = 10000;
        int res = inf;

        Function<Integer, Integer> root = i -> {
            List<Integer> dis = new ArrayList<>(Collections.nCopies(n, inf));
            int localMin = inf;
            dis.set(i, 0);
            Queue<Integer> bfs = new LinkedList<>(Arrays.asList(i));
            while (!bfs.isEmpty()) {
                i = bfs.poll();
                for (int j: g.get(i)) {
                    if (dis.get(j) == inf) {
                        dis.set(j, 1 + dis.get(i));
                        bfs.offer(j);
                    }
                    else if (dis.get(i) <= dis.get(j)) {
                        localMin = Math.min(localMin, dis.get(i) + dis.get(j) + 1);
                    }
                }
            }
            return localMin;
        };

        for (int i = 0; i < n; i++) res = Math.min(res, root.apply(i));

        return res == inf ? -1 : res;
    }
}
