/**
https://leetcode.com/problems/evaluate-division/description/

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.
**/
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        Map<String,Map<String,Double>> graph = makeGraph(equations,values);
        double[] d=new double[queries.size()];
        
        for(int i=0;i<queries.size();i++){
            d[i]=getPathWeight(queries.get(i).get(0),queries.get(i).get(1),new HashSet<>(),graph);
        }
        return d;
      
    }




    private double getPathWeight(String start, String end, Set<String> visited,Map<String,Map<String,Double>> graph ){
        if(!graph.containsKey(start)){
            
            return -1.0;
        }
        if(graph.get(start).containsKey(end))return graph.get(start).get(end);
        visited.add(start);
        for(Map.Entry<String, Double> m: graph.get(start).entrySet()){
            if(!visited.contains(m.getKey())){
                double wt=getPathWeight(m.getKey(),end,visited,graph);
                if(wt!=-1.0){
                    return wt*m.getValue();
                }
            }
        }
        
        return -1.0;
    }

    private Map<String,Map<String,Double>> makeGraph(List<List<String>> e, double[] values){
        Map<String,Map<String,Double>> g=new HashMap<>();
        String u,v; 
        for(int i=0;i<e.size();i++){
            u=e.get(i).get(0);
            v=e.get(i).get(1);
            g.putIfAbsent(u,new HashMap<>());
            
            g.get(u).put(v,values[i]);
            g.putIfAbsent(v,new HashMap<>());
            g.get(v).put(u,1/values[i]);
        }
        return g;
    }

}
