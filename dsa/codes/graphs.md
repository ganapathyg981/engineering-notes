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