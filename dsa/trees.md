# Trees
<p align="center" style="width:50vw">
  <img src="https://journaldev.nyc3.cdn.digitaloceanspaces.com/2020/08/fully-balanced-1024x512.png" alt="Sublime's custom image"/>
<br>
<i>Binary Tree</i>
</p>  

## General Tips
- When you wanna identify sub-trees use postorder

## Patterns
- DFS top-down or bottom-up moving 
- BFS
- BST problems

## DFS
You basically drill down to the end for the above tree, 1295365 types  
- Also you have inorder, preorder, and postorder
- inorder of a BST it always sorted - left root right
- first element ofa preorder is always root - root left right
- postorder  - left right root

## BFS
You go through levels, you must use queue for doing this. 1239565 -> for above tree  
- Add root to queue 
- Take Queue's size as level size
- loop through level size and poll node do stuff
- add left and right to queue

### Invert Binary Tree
#### Intuitions & Solution

- Do DFS
- swap left and right nodes (use temp var you idiot) and return node

### Max height of B'Tree
#### Intuitions & Solution

- Do DFS
- base case at null node return 0
- then return max(1+height(left) & 1+height(right))

### Same Tree
#### Intuitions & Solution
- Do DFS on both root nodes
- base case return true if both tails are null
- if either is not null and one is null, return false
- if nodes are not equal return false;
- return on same(left) and same(right)

### Sub-tree 
#### Intuitions & Solution
 
- Do DFS top down approach
- base case when you reach main's leaf and you still didnt find shit.. then return false
- if secondary is null return true
- call same tree on root, if yes return true
- return  isSubTree(left) || isSubTree(right)  

### Lowest Common Ancestor of BST
#### Intuitions & Solution

- Since it's a BST you can do some time saving, but below is basic code
- Do a DFS with bottom up
- Base case return null
- if(node==a || node==b) return node
- left=call(node.left,a,b);
- right=call(node.right,a,b);
- if(left && right not null)  return root
- if(left) return left
- if(right) return right;

you can ignore to call a left node and return null if left is > a&&b same other way

### Construct tree from inorder and preorder
#### Intuitions & Solution

### Binary Tree Maximum Path Sum
#### Intuitions & Solution
- Alright, This is after some long time, writing helps, keep doin
- Trick part here is you have negative numbers which sometimes grills you
- So whenever a subtree sum is negative ignore it (Math.max(0,left+val))
- keep track of max sum, you might lose it midway too
- left  = max(0,func(left))  right  = max(0,func(right))
- cur = val+left+right
- return val+max(left,right)


### Equal partition sum
#### Intuitions & Solution
- Get the whole sum of tree
- Divide by 2
- If you get a sub-tree with half of the sum, its there


### Count Good Nodes in Binary Tree
#### Intuitions & Solution
- DFS and drill down the largest node
- whenever you encounter the even largest, count increment

### Serialize And Deserialize Binary Tree
#### Intuitions & Solution
- We should write two methods, one to encode as string and decode from string
- The trick is we need to factor in nulls
- use commas to separate nodes with commas value, or *,

### Collect Apples Minimum Time
#### Intuitions & Solution
- Go bottom up in this
- Coz you wanna calculate apple numbers from bottom
- Base case 0
- if apple found or prev return values >0 (coz we found an apple earlier and we have to take it to top), return 2
- init 0 before recursion
- accumulate sum of return values

### Inform Employees Max time
#### Intuitions & Solution
- Go top down in this
- Coz info pass from manager to subs
- Keep track of max time
- Pass currentTime in args
- return max of all times, for that keep a 0 init var

### Sum of numbers if each number is root to leaf
#### Intuitions & Solution
- Go top down in this
- Coz you can pass the first digit everytime and multiply by 10
- At last return the one number
- Pass each number in args
- return sum of all numbers returned which is left+right
### Smallest String
#### Intuitions & Solution
- Go top down in this
- Coz you can prepend everytime to build the string
- At the end return the string
- compare the smallest and return out // refer code once
###  Delete Leaves With a Given Value
#### Intuitions & Solution
- Go bottom up in this
- Bcoz if u remove a leaf node, the parent might become leaf
- We have to keep that in check too
- Always remember bottom-up the return has the logic

###  Duplicate Sub-Trees
#### Intuitions & Solution

###  Make the tree a String
#### Intuitions & Solution

### Valid BST
#### Intuitions & Solution

- ok.. so the trick part is, not only left<root<right
- also all stuff under left should be lesser than root
- all stuff under right should be greater than root
- So we pass alone some boundaries top down
- take left right as imaginary boundary to each node
- condition should be like node is between left and right
- left = INT_MIN right = INT_MAX
- when you go to left node right will become current
- when you go to a right node left will become current

### Kth smallest element in BST
#### Intuitions & Solution

- as we came to know before, inorder is in sorted for BST
- take in a state having the output and counter for inorder
- everytime you process an element increment counter
- if(counter =k) return that output

### Unique BST 2
#### Intuitions & Solution

### Binary Tree Right Side View
#### Intuitions & Solution
- Do BFS
- Last element of each level is right view