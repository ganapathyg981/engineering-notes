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
You are given a m × n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a
treasure chest than the value should remain INF.

Assume the grid can only be traversed up, down, left, or right.

````json
"Input": [
  [2147483647,-1,0,2147483647],
  [2147483647,2147483647,2147483647,-1],
  [2147483647,-1,2147483647,-1],
  [0,-1,2147483647,2147483647]
]

"Output": [
  [3,-1,0,1],
  [2,2,1,-1],
  [1,-1,2,-1],
  [0,-1,3,4]
]
````
#### Intuitions & Solutions
- Ok, this is BFS!! Why? you have to find the shortest distance / unweighted graph
- Then since it is for multiple sources, this is multi source BFS
- Also dummy, the distance from land to treasure is same as the distance from treasure to land
- Add all treasure as source to queue
- Do a BFS on unvisited nodes
- <b>If you have already visited a land cell, other farthest cells would not overwrite visited cells</b>
### Pacific Atlantic Water Flow
You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. Water can also flow into the ocean from cells adjacent to the ocean.

Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/3899fae1-ab18-4d6b-15b4-c7f7aa224700/public" alt="Undirected Graph"/>
  <br>
  <i></i>
</p>

```
Input: heights = [
  [4,2,7,3,4],
  [7,4,6,4,7],
  [6,3,5,3,6]
]

Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]

```

### Course Schedule
You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

The pair [0, 1], indicates that must take course 1 before taking course 0.

There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return true if it is possible to finish all courses, otherwise return false.
```
Input: numCourses = 2, prerequisites = [[0,1]]
Output: true //Explanation: First take course 1 (no prerequisites) and then take course 0.
Input: numCourses = 2, prerequisites = [[0,1],[1,0]]
Output: false // Explanation: In order to take course 1 you must take course 0,
and to take course 0 you must take course 1. So it is impossible.
```
### Course Schedule II
You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. If it's not possible to finish all courses, return an empty array.
``
Input: numCourses = 3, prerequisites = [[1,0]]

Output: [0,1,2]
Explanation: We must ensure that course 0 is taken before course 1.
``
### Course Schedule IV
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.
<p align="center" style="width:50vw">
  <img src="https://assets.leetcode.com/uploads/2021/05/01/courses4-1-graph.jpg" alt="Undirected Graph"/>
  <br>
  <i></i>
</p>

```
Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
```
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
### Find the closest node to given two node
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