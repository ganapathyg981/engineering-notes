package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*

 */
public class Graph {

    private final Map<Integer, List<Integer>> edges;

    Graph (){
        edges = new HashMap();
    }

    public void addEdge(int source, int dest) {
        List<Integer> connections = edges.get(source);
        if (connections == null) {
            connections = new ArrayList<>();
            edges.put(source, connections);
        }
        connections.add(dest);
    }

    public List<Integer> getEdges(int source) {
        return edges.get(source)==null? Collections.emptyList():edges.get(source);
    }

    public void dfs (int source) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(source, visited);

    }

    public void printEdges() {
        for (int i = 0; i < 5; i++) {
            List<Integer> edges = this.getEdges(i);
            System.out.println("Edges from node " + i + ": " + edges);
        }
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.println(current);
        List<Integer> neighbors = getEdges(current);
        if(neighbors==null || neighbors.isEmpty()) {
            return;
        }
        for(Integer neighbor: neighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }

    }

    public void bfs(int source) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            System.out.println(current);
            List<Integer> neighbors = getEdges(current);
            if(neighbors != null){
                for (Integer neighbor: neighbors) {
                    if(!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

        }
    }

    public List<List<Integer>> bfsCumulate(int source) {
        List<List<Integer>> bfs = new ArrayList<>();
        bfsCumulateHelper(source, bfs);
        System.out.println(bfs);
        return bfs;
    }

    private void bfsCumulateHelper(int source, List<List<Integer>> bfs) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        bfs.add(List.of(source));
        queue.add(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            List<Integer> neighbors = getCurrentLevelNeighbours(queue, visited);
            if(!neighbors.isEmpty()) {
                bfs.add(neighbors);
                queue.addAll(neighbors);
            }
        }
    }

    private List<Integer> getCurrentLevelNeighbours(Queue<Integer> queue, Set<Integer> visited) {
        List<Integer> neighbors = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            List<Integer> integers = getEdges(current);
            for (Integer integer: integers) {
                if(!visited.contains(integer)) {
                    neighbors.add(integer);
                    visited.add(integer);
                }
            }
        }
        return neighbors;
    }

    public List<List<Integer>> bfsCumulateV2(int source) {
        List<List<Integer>> bfs = new ArrayList<>();
        bfsCumulateHelper(source, bfs);
        System.out.println(bfs);
        return bfs;
    }

    private void bfsCumulateHelperV2(int source, List<List<Integer>> bfs) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            // Get current level size
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // TODO Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                Integer current = queue.poll();
                currentLevel.add(current);

                // Add all unvisited neighbors
                List<Integer> neighbors = getEdges(current);
                for (Integer neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Add the current level to the BFS result
            bfs.add(currentLevel);
        }
    }
}

