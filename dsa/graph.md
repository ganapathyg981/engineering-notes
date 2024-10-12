# Graphs

Consists of a set of vertices (or nodes) and edges that connect pairs of vertices.
## Intuition - When to Use
Graphs are particularly useful when you need to represent and analyze relationships between objects. Use graphs when:
- **Modeling Relationships**: When you want to model pairwise relationships between entities, such as friends in a social network or cities connected by roads.
- **Pathfinding Problems**: When you need to find the shortest or most efficient path between two points, such as in navigation systems or networking.
- **Network Flow**: When you need to analyze the flow of resources through a network, such as traffic in a transportation system or data packets in a computer network.
- **Hierarchy Representation**: When you want to represent hierarchical structures, like organizational charts or file systems.
- **Dependency Resolution**: When you need to manage dependencies between tasks, such as in build systems or scheduling algorithms, where certain tasks must be completed before others.

## Intuition - Exacts
- #### Dependencies, Find order of completion, can we cover all nodes
  - Topological Sort with Cycle detection for DAGs
- #### Shortest Path, Minimum moves/modifications with connected components  
  - BFS for unweighted
  - Dijkstra for one to all distance with positive weight edges
  - Bellman-Ford for negative weight edges
  - Floyd-Warshall for all to all distance
- #### Connected components, Minimum/maximum of connected components, Number of nodes in connected components, Redundant Nodes/Connections, Minimum Spanning Tree (Kruskal's Algo), Merging intervals??, check connectivity
  - Disjoint Set
  - Only on undirected
- #### Number of islands/ anything like that
  - DFS
- #### DAG/ Directed Graph/ Finding order/ Detecting cycles
  - Topological sort with Stack
  - Cycle detection with Visited/Visiting/Unvisited states
- #### Undirected cycle detection / Valid tree
  - DFS with parent passing, ignore if next node is parent
  - Union find disjoint set, only with parent u can do it, coz we dont need depth info

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

## Problems & Learnings
### Rotten Oranges   
   You are given an m x n grid where each cell can have one of three values:
  - 0 representing an empty cell
  - 1 representing a fresh orange
  - 2 representing a rotten orange  
  
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
<p align="center" style="width:50vw">
  <img src="https://assets.leetcode.com/uploads/2019/02/16/oranges.png" alt="Undirected Graph"/>
  <br>
  <i>Rotten Oranges</i>
</p>

#### Intuitions & Solution
- You have to spread from a point/group, so BFS
- Source is rotten oranges to get a queue with **indexes of rotten oranges**
- Have a count of fresh oranges for edge cases
- Do **BFS with levels by queue size** so that you know each step
- Have an array of possible move offsets

### Surrounded Regions
You are given a 2-D matrix board containing 'X' and 'O' characters.
If a continuous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.

Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.

<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/9e6916bf-0e25-4e15-9619-cbc42d2d8f00/public" alt="Undirected Graph"/>
  <br>
  <i>Surrounded Regions</i>
</p>

#### Intuitions & Solution
- You had to think a little different here
- You cannot simply select all Os and BFS to see surroundings, its a little complex
- There are only two possibilities, O is surrounded by X or O is surrounded by Border
- If you find all Os which is by border, then all other Os are for sure surrounded by X
- Do a DFS on Os by border and mark them as #
- You will have a grid of O,X and #
- replace Os with X for the goal, replace # with O to restore original grid

### Number of Islands
Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.

An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water.
You may assume water is surrounding the grid (i.e., all the edges are water)  
````
Input: grid = [
["0","1","1","1","0"],
["0","1","0","1","0"],
["1","1","0","0","0"],
["0","0","0","0","0"]
]
Output: 1
````
####  Intuitions & Solution
- It's a dfs code for sure. Why? You have to drill down to the island
- You should have offset. to see possible moves, Also keep track of count in dfs.
- And have a visitedSet, actually mark cells as zero for visited,
- <b>Remember, For noting 2-Dimensions as visited, use a 2D array</b>
- For every break in the outer iteration / every return from DFS, you keep adding counts
### Max Area of Island
Max Area of Island
You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).

An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the grid are surrounded by water.

The area of an island is defined as the number of cells within the island.

Return the maximum area of an island in grid. If no island exists, return 0.
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/8eeb491c-c8ff-4ed6-78ed-ce4cf87d7200/public" alt="Undirected Graph"/>
  <br>
  <i>Max Area of islands is 6</i>
</p>

### Clone Graph
Given a node in a connected undirected graph, return a deep copy of the graph.

Each node in the graph contains an integer value and a list of its neighbors.

````java
class Node {
public int val;
public List<Node> neighbors;
}
````

<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/ca68c09d-4d0e-4d80-9c20-078c666cf900/public" alt="Undirected Graph"/>
  <br>
  <i>Clone this shit!</i>
</p>

#### Intuitions & Solution
- Alright, we're doing a dfs and going deep into the given node
- Create a root node, so that u can return to them asker
- Have a modified DFS function, pass the source root, clone root
- Also have a Map of original node to cloned nodes for the following reasons
  - To prevent duplicate traversal leading to infinite loops
  - Also, to keep one for one clone. See the code if required
### Walls and Gates

### Pacific Atlantic Water Flow
### Course Schedule
### Course Schedule II
### Course Schedule IV
### Find the Town Judge
### Count Sub Islands
### Reorder Routes to Make All Paths Lead To The City
### Snakes and Ladders
### Open The Lock
### Find Eventual Safe States
### Graph Valid Tree
### Check If Move Is Legal
### Shortest Bridge
### Shortest path in binary matrix
### Number of connected components in an undirected graph
### Redundant connection
### Accounts merge
### Find closest node to given two node
### As Far from Land as Possible
### Shortest Path with Alternating Colors
### Minimum Fuel Cost to Report to the Capital
### Minimum Score of a Path Between Two Cities
### Number of Enclaves
### Minimum Number of Vertices to Reach all Nodes
### Is Graph Bipartite?
### Detonate the Maximum Bombs
### Minimum Height Trees
### Path with Maximum Gold
### Word Ladder
### Reconstruct Itinerary
### Min Cost to Connect All Points
### Network Delay Time
### Swim In Rising Water
### Alien Dictionary
### Cheapest Flights Within K Stops