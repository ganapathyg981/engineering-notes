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
- #### Weird shit questions might involve indegree and outdegree

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
- Only on undirected
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
### Minimum Height Trees
### Pacific Atlantic Water Flow
### Course Schedule IV
### Rotten Oranges

#### Intuitions & Solution
- You have to spread from a point/group, so BFS
- Source is rotten oranges to get a queue with **indexes of rotten oranges**
- Have a count of fresh oranges for edge cases
- Do **BFS with levels by queue size** so that you know each step
- Have an array of possible move offsets

### Surrounded Regions

#### Intuitions & Solution
- You had to think a little different here
- You cannot simply select all Os and BFS to see surroundings, its a little complex
- There are only two possibilities, O is surrounded by X or O is surrounded by Border
- If you find all Os which is by border, then all other Os are for sure surrounded by X
- Do a DFS on Os by border and mark them as #
- You will have a grid of O,X and #
- replace Os with X for the goal, replace # with O to restore original grid

### Number of Islands
####  Intuitions & Solution
- It's a dfs code for sure. Why? You have to drill down to the island
- You should have offset. to see possible moves, Also keep track of count in dfs.
- And have a visitedSet, actually mark cells as zero for visited,
- <b>Remember, For noting 2-Dimensions as visited, use a 2D array</b>
- For every break in the outer iteration / every return from DFS, you keep adding counts
### Max Area of Island

### Clone Graph

#### Intuitions & Solution
- Alright, we're doing a dfs and going deep into the given node
- Create a root node, so that u can return to them asker
- Have a modified DFS function, pass the source root, clone root
- Also have a Map of original node to cloned nodes for the following reasons
  - To prevent duplicate traversal leading to infinite loops
  - Also, to keep one for one clone. See the code if required
### Walls and Gates
#### Intuitions & Solutions
- Ok, this is BFS!! Why? you have to find the shortest distance / unweighted graph
- Then since it is for multiple sources, this is multi source BFS
- Also dummy, the distance from land to treasure is same as the distance from treasure to land
- Add all treasure as source to queue
- Do a BFS on unvisited nodes
- <b>If you have already visited a land cell, other farthest cells would not overwrite visited cells</b>

### Course Schedule
#### Intuitions & Solution
- Build a cycle detection DFS and have a stack for topo sort
- if cycle detected,then false
- if stack size is not node size, then false
### Course Schedule II
#### Intuitions & Solution
- Build a cycle detection DFS and have a stack for topo sort
- if cycle detected,then false
- if stack size is not node size, then false
- else return stack popped list.

### Find the Town Judge
#### Intuition & Solution
  - Think about in-degree and out-degree of this connections
  - The judge should have 0 out and n-1 in
  - Else you can't make it

### Count Sub Islands
https://leetcode.com/problems/count-sub-islands/description/
### Intuitions & Solution
- Ok, you already know this grouping thing comes under DFS
- the second one's island should also be a island in first one
- So, DFS on second ones cells, use grid1's to decide to move on
- mark cells as zero to remember visited stuff
- finally return the count of islands which are there in both the grids

### Reorder Routes to Make All Paths Lead To The City (Code this when free, seems like a challenge)
### Intuitions & Solution
- New trick alert here.
- Consider this as an undirected graph and add false edges to adj list
- To identify the false edges, use negative (assuming all are positive nodes)
- Do a BFS from zero, if u see a false edge as incoming, then add count
- Hola!
### Snakes and Ladders (Code this when free, seems like a challenge)
https://leetcode.com/problems/snakes-and-ladders/
#### Intuitions & Solution
- Ok brother, here we go!
- Shortest path, so BFS
- your possible moves are n+(1to6)
- whenever u land a cell, check for snakes/ladders and move into proper result position
- when u land at final destination, return count
### Open The Lock
https://leetcode.com/problems/open-the-lock/description/
#### Intuitions & Solution
- Yep, we got it.
- Minimum moves so BFS
- your start node is 0000
- possible moves are each digit one up, If its 9 then 0
- If it's not in deadlock then only add it in next iteration
- you will get the count now
- New trick alert
- For leveled BFS, you can add another while loop too
``` scala
int level=0;
        while(!queue.isEmpty())
        {
            int size =queue.size();
            while(size-- >0)
            {
                String current =queue.poll();
                if(deadendSet.contains(current))continue;
                if(current.equals(target)) return level;

                List <String> nextop= nextOptions(current);
                for(String op : nextop)
                {
                    if(!visited.contains(op))
                    {
                        queue.offer(op);
                        visited.add(op);
                    }
                }
            }
            level++;
        }
```
### Find Eventual Safe States (Code this when free, seems like a challenge)
https://leetcode.com/problems/find-eventual-safe-states/description/
#### Intuitions & Solution
- These are the questions which might duck you, Also new trick alert
- This involves a BFS similar approach
- Start with nodes with no outdegrees, add them to the queue
- Maintain  a reverse adjacency list, when there is 0->1   have 1->0
- poll and add them to safe state
- Get indegrees of that node, reduce the outdegree of them by 1, since we removed the leaf
- If the new outdegree is 0, add them to queue
- do shit while queue is empty

### Graph Valid Tree
https://neetcode.io/problems/valid-tree
#### Intuitions & Solution
- A Tree is a connected graph with no cycles
- These are undirected edges, and we need to find connection and cycles, we can use union find.
- There is a slight trick here for checking connected condition
- There should be n-1 edges as input, if less or more it's a problem
- If equals with cycle edge, union will find it.
### Check If Move Is Legal (Code this when free, seems like a challenge)
https://leetcode.com/problems/check-if-move-is-legal/description/
#### Intuitions & Solution 
- It's like the island thingy so, DFS.
- Have all the directions handy in a list, pass it to DFS, because once u go there you have to go in that direction only!!
- Don't pass the next row and make it complicated, pass the current cell and going directed, so propagate for all non '.' neighbors
- If its the same color but the length of the color is <=2, then false
- Else if less then 2
- If '.' neighbor then return false;
- If its diff color, move on with increasing length
- New trick alert!!! When they ask only one condition is enough on iterations, don't wait on all, return or break out immediately after one satisfaction
### Shortest Bridge (Code this when free)
https://leetcode.com/problems/shortest-bridge/description/
#### Intuitions & Solution
- Shortest Path, Hands down BFS
- Advantage here is we know there are exactly 2 islands
- Have a visited grid array or mark one island with #
- Do a multi source BFS with all cells on the island to find the closest 1.
- Track the levels and get it tiger
### Shortest path in binary matrix
https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
#### Intuitions & Solution
- This is simple BFS
- The path should all be zero, so if top left or bottom right is not zero return false
- If there is single element and its zero, return true <b>Learn/Focus on Edge cases</b>
- New Trick !!! You can pass lengths in queue when doing BFS too.
- Do BFS in all directions, if u reached bottom left return length
### Number of connected components in an undirected graph
https://neetcode.io/problems/count-connected-components
#### Intuitions & Solution
- This is exactly why we have union-find
- Do union on all edges
- Unique parents on parent array is the count
- Always use your DS1 class, read it!!!
### Redundant connection
https://neetcode.io/problems/redundant-connection
#### Intuitions & Solution
- Bro, undirected redundant edge is cycle, union find all the way
- do union on all edges
- First edge to present cycle, is the culprit
### Accounts merge (Code this when free, seems like a challenge)
https://leetcode.com/problems/accounts-merge/description/
#### Intuitions & Solution
- Took a while to understand this is a connected component problem on union disjoint 
- Since these aren't 'n' nodes, make DS1 implementation on a HashMap
- Also have a email to name map.
### As Far from Land as Possible  (Code this when free, seems like a challenge)
https://leetcode.com/problems/as-far-from-land-as-possible/description/
#### Intuitions & Solution
- New Trick Alert!!!
- This is also BFS, but here we need the max distance
- Multi-source BFS from all 1s
- When u meet a zero increase the distance by 1, (you are overriding cell values for distances)
- move to until u have 0s in ur four direction
- have a max global variable
### Shortest Path with Alternating Colors (Code this when free, seems like a challenge)
https://leetcode.com/problems/shortest-path-with-alternating-colors/description/
#### Intuitions & Solution
- New Trick alert
- BFS all the way bruh
- But have two adj list
- Start with 0 and add (0,0,0) and (0,0,1)  first one is node, second is distance and last one is color
- If the current color is red, then do BFS on blue vice-versa
### Minimum Fuel Cost to Report to the Capital
https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
#### Intuitions & Solution
- Dead meat Alert!!
- This made u doubt your life
- YOU thought a lot about BFS
- But this is DFS
- You need to start your ride from the end nodes to source
- Bottom up
- Also, u need to pass information from every node to next
- There was a formula fucking read that //TODO still don't know
- For every person, calculate number of persons start with 1 and add bottom up
- You have number of persons, number of seats, calculate fuel burnt for next stop
- when u reach the one stop before destination, which is > 0 here 
### Minimum Score of a Path Between Two Cities
https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/
#### Intuitions & Solution
- undirected graph
- it is asking for a minimum edge (score) in the path
- So u can just do union find and keep track of min edge weight
- when its over, get the min edge of the parent of 1 & n
###  Number of Closed Islands
https://leetcode.com/problems/number-of-closed-islands/description/
#### Intuitions & Solution
- Alrighty, So if u eliminate all the zeros which shares border
- All u have is zeros surrounded by 1s
- then count island with DFS and return
### Number of Enclaves
https://leetcode.com/problems/number-of-enclaves/description/
#### Intuitions & Solution
- Same as last one
### Minimum Number of Vertices to Reach all Nodes
https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/description/
#### Intuitions & Solution
- Any node with indegree can always be traversed from other nodes
- Any node with no indegree, should be traversed fro sure
- So the answer is just the nodes with zero in-degree
### Is Graph Bipartite?
https://leetcode.com/problems/is-graph-bipartite/description/
#### Intuitions & Solution
- The description was confusing and twisty
- It is basically a graph coloring problem
- If u color every node some color and next node to another.
- and if u can make it possible throughout the graph, then it is bipartite
- So for coloring you have to do a BFS with some twist
- Take one color as -1, another as 1
- Start with -1 as current  colo, multiply by -1  and set to current color for next color
- you can have a map or array based on sparse or dense graph

### Detonate the Maximum Bombs
https://leetcode.com/problems/detonate-the-maximum-bombs/
#### Intuitions & Solution
- Just find a way to if a bomb is reachable from another using formulas
- Make an adjacency list and DFS for max island type
### Path with Maximum Gold
https://leetcode.com/problems/path-with-maximum-gold/description/
#### Intuitions & Solution
- Ok, there is no start or end constraint here
- just do DFS start with gold and end with gold
- don't go to 0s and visited nodes
- keep track of max gold collected 
### Word Ladder
https://neetcode.io/problems/word-ladder
#### Intuitions & Solution
- Take deep breath Nanba
- You have to change from one state to another, Also note that all words are same length
- You have only some possible changes applicable
- Check if end word exist in possibilities
- Construct all possible changes e.g. cat -> *at, c*t, ca*
- You should view this as a graph
- You make a map of possibility and words applicable eg. *at -> (cat,sag,bag)
- Also have a visited Set to avoid cycles
- Do a BFS from start word.
- Get all mutations and possible neighbor
- Do until we find the end word and return count
### Reconstruct Itinerary
https://neetcode.io/problems/reconstruct-flight-path
#### Intuitions & Solution
- Simple Topo sort nanba
- Just that here u have to sort all adj nodes / have a pq
- Start from JFK
### Min Cost to Connect All Points
https://neetcode.io/problems/min-cost-to-connect-points
#### Intuitions & Solution
- New Trick Alert!!!
- For all two combination, for i loop inside that i+1 loop
- Prepare an adj list with all to all nodes
- Calculate distance with formula and thats your edge weight
- Put all edges in a PQ
- Have a visited set and a node counter
- while node counter <= actual node count
- Pick minimum edges and add cost until add nodes are picked
### Network Delay Time
https://neetcode.io/problems/network-delay-time
#### Intuitions & Solution
- Start From source node
- Do Dijkstra also keep adding weights
- You should keep a distance array to maintain min distance
- It will be filled with INT_MAX for all and zero for source
- Ans will be sum of distance array value
### Swim In Rising Water
https://neetcode.io/problems/swim-in-rising-water
####  Intuitions & Solution
- Easy peasy!
- Modified Dijkstra
- Start with weight of (0,0),0,0
- Have max weight in path and coordinates on PQ and do BFS
- Track max weight encountered along the path
- Return that max weight when reaching last node include last node's weight too
### Alien Dictionary
https://neetcode.io/problems/foreign-dictionary
####  Intuitions & Solution
- Bro, just don't hesitate
- Make an adj list with empty list for every unique letter in the list of words
- Take two words at a time.
- If first word is bigger than second and both starts with same char then invalid error
- Find the first differing char add it as an edge
- Do a Topo sort, Beware of cycles that make the thing invalid
- Visited, Visiting, Unvisited
### Cheapest Flights Within K Stops
https://neetcode.io/problems/cheapest-flight-path
####  Intuitions & Solution
- Dijkstra but with atmost k hops
- Keep track of hops with double while technique or passing the hop in queue
- Keep track of min distance with a distance array
- Thats it MF