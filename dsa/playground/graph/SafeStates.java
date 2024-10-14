package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        
        ArrayList<Integer>[] arr = new ArrayList[n];
        for(int i=0; i<n; i++) arr[i] = new ArrayList<>();
        int[] outdegree = new int[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<graph[i].length; j++){
                arr[graph[i][j]].add(i);
                outdegree[i]++;
            }
        }
        int[] safe = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) if(outdegree[i]==0) q.add(i);
        System.out.println(Arrays.deepToString(arr));
        while(!q.isEmpty()){
            int a = q.poll();
            safe[a] = 1;
            for(var x : arr[a]){
                outdegree[x]--;
                if(outdegree[x]==0) q.add(x);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(safe[i]==1) ans.add(i);
        }
        return ans;
        
    }

    public static void main(String[] args) {
        SafeStates safeStates = new SafeStates();
        int[][] x= new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};

        System.out.println(safeStates.eventualSafeNodes(x));

    }
}