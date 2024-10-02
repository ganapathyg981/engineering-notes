package graph;

public class Test {
    public static void main(String[] args) {
            Graph graph = new Graph();
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 3);
            graph.addEdge(2, 3);
            graph.addEdge(2, 5);
            graph.addEdge(3, 4);
//            graph.printEdges();
//            graph.dfs(0);
//            graph.bfs(0);
        graph.bfsCumulate(0);
        graph.bfsCumulateV2(0);
    }
}
