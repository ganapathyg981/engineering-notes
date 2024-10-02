# Graphs

Consists of a set of vertices (or nodes) and edges that connect pairs of vertices.
## Intuition - When to Use
Graphs are particularly useful when you need to represent and analyze relationships between objects. Use graphs when:
- **Modeling Relationships**: When you want to model pairwise relationships between entities, such as friends in a social network or cities connected by roads.
- **Pathfinding Problems**: When you need to find the shortest or most efficient path between two points, such as in navigation systems or networking.
- **Network Flow**: When you need to analyze the flow of resources through a network, such as traffic in a transportation system or data packets in a computer network.
- **Hierarchy Representation**: When you want to represent hierarchical structures, like organizational charts or file systems.
- **Dependency Resolution**: When you need to manage dependencies between tasks, such as in build systems or scheduling algorithms, where certain tasks must be completed before others.

Graphs can model both simple and complex relationships, making them versatile for various applications in computer science, mathematics, and engineering.

## Types of Graphs

### Directed and Undirected
<p align="center" style="width:50vw">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_QxM7ua0JWeaACBxQ8TsmzHMzQ0SPeWVLbw&s" alt="Directed Graph"/>
  <br>
  <i>Directed Graph</i> 
</p>

<p align="center" style="width:50vw">
  <img src="https://www.tutorialspoint.com/data_structures_algorithms/images/undirected_graph.jpg" alt="Undirected Graph"/>
  <br>
  <i>Undirected Graph</i>
</p>

### Weighted and Unweighted
<p align="center" style="width:50vw">
  <img src="https://media.geeksforgeeks.org/wp-content/uploads/graphhh.png" alt="Weighted Graph"/>
  <br>
  <i>Weighted Graph</i>
</p>

<p align="center" style="width:50vw">
  <img src="https://aquarchitect.github.io/swift-algorithm-club/Shortest%20Path%20%28Unweighted%29/Images/Graph.png" alt="Unweighted Graph"/>
  <br>
  <i>Unweighted Graph</i>
</p>

## Adjacency List and Matrix

### Adjacency List
An adjacency list is a collection of lists or arrays that represent a graph. Each vertex has a list of adjacent vertices. It is memory efficient for sparse graphs.

**Example**:
```
Vertex 0: 1 -> 2
Vertex 1: 0 -> 3
Vertex 2: 0
Vertex 3: 1
```

### Adjacency Matrix
An adjacency matrix is a 2D array where each cell at position (i, j) indicates the presence (1) or absence (0) of an edge between vertex i and vertex j. It is memory efficient for dense graphs.

**Example**:
```
    0 1 2 3
  0 0 1 1 0
  1 1 0 0 1
  2 1 0 0 0
  3 0 1 0 0
```

# Toolset
## Traversal
## Depth-First Search (DFS)
- ### DFS Implementation for Adjacency List
    ```java
        import java.util.*;
        
        class Graph {
            private List<List<Integer>> adjList;
        
            public Graph(int vertices) {
                adjList = new ArrayList<>(vertices);
                for (int i = 0; i < vertices; i++) {
                    adjList.add(new ArrayList<>());
                }
            }
        
            public void addEdge(int u, int v) {
                adjList.get(u).add(v);
                adjList.get(v).add(u); // for undirected graph
            }
        
            public void DFS(int start) {
                boolean[] visited = new boolean[adjList.size()];
                DFSUtil(start, visited);
            }
        
            private void DFSUtil(int vertex, boolean[] visited) {
                visited[vertex] = true;
                System.out.print(vertex + " ");
                for (int neighbor : adjList.get(vertex)) {
                    if (!visited[neighbor]) {
                        DFSUtil(neighbor, visited);
                    }
                }
            }
        }
        ```
    
 - ### DFS Implementation for Adjacency Matrix
    ```java
    class Graph {
        private int[][] adjMatrix;
    
        public Graph(int vertices) {
            adjMatrix = new int[vertices][vertices];
        }
    
        public void addEdge(int u, int v) {
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1; // for undirected graph
        }
    
        public void DFS(int start) {
            boolean[] visited = new boolean[adjMatrix.length];
            DFSUtil(start, visited);
        }
    
        private void DFSUtil(int vertex, boolean[] visited) {
            visited[vertex] = true;
            System.out.print(vertex + " ");
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    DFSUtil(i, visited);
                }
            }
        }
    }
    ```

- ## Breadth-First Search (BFS)
  - ### BFS Implementation for Adjacency List
      ```java
      import java.util.*;
    
      class Graph {
          private List<List<Integer>> adjList;
    
          public Graph(int vertices) {
              adjList = new ArrayList<>(vertices);
              for (int i = 0; i < vertices; i++) {
                  adjList.add(new ArrayList<>());
              }
          }
    
          public void addEdge(int u, int v) {
              adjList.get(u).add(v);
              adjList.get(v).add(u); // for undirected graph
          }
    
          public void BFS(int start) {
              boolean[] visited = new boolean[adjList.size()];
              Queue<Integer> queue = new LinkedList<>();
              visited[start] = true;
              queue.offer(start);
    
              while (!queue.isEmpty()) {
                  int vertex = queue.poll();
                  System.out.print(vertex + " ");
                  for (int neighbor : adjList.get(vertex)) {
                      if (!visited[neighbor]) {
                          visited[neighbor] = true;
                          queue.offer(neighbor);
                      }
                  }
              }
          }
      }
      ```

  - ### BFS Implementation for Adjacency Matrix
      ```java
      import java.util.*;
    
      class Graph {
          private int[][] adjMatrix;
    
          public Graph(int vertices) {
              adjMatrix = new int[vertices][vertices];
          }
    
          public void addEdge(int u, int v) {
              adjMatrix[u][v] = 1;
              adjMatrix[v][u] = 1; // for undirected graph
          }
    
          public void BFS(int start) {
              boolean[] visited = new boolean[adjMatrix.length];
              Queue<Integer> queue = new LinkedList<>();
              visited[start] = true;
              queue.offer(start);
    
              while (!queue.isEmpty()) {
                  int vertex = queue.poll();
                  System.out.print(vertex + " ");
                  for (int i = 0; i < adjMatrix.length; i++) {
                      if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                          visited[i] = true;
                          queue.offer(i);
                      }
                  }
              }
          }
      }
      ```
## Topological Sort
- ### DFS with Stack
- ### Kahn's Algo
## Shortest Path
- ### BFS (when no weights)
- ### Dijkstra (when weighted edges)
- ### Bellman-Ford (when negative weight edges)
- ### Floyd-Warshall (when distance needed for all to all)
## Disjoint Set
Can be used for
- Grouping connected nodes
- Getting some information like count of nodes/ min weight of edges/ sum of edges etc..
- ### Implementation
  - #### Arrays for fixed nodes
  - #### Map for sparse nodes
- ### Minimum Spanning Tree (Kruskal)
- ### Connected Components
- ### Redundant connection removal
## Minimum Spanning Tree
- ### Prim's Algo 
- ### Kruskal's Also