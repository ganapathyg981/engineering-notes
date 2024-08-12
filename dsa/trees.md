# Trees
<p align="center" style="width:50vw">
  <img src="https://journaldev.nyc3.cdn.digitaloceanspaces.com/2020/08/fully-balanced-1024x512.png" alt="Sublime's custom image"/>
<br>
<i>Binary Tree</i>
</p>  

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

## Invert Binary Tree
You are given the root of a binary tree root. Invert the binary tree and return its root.  
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/ac124ee6-207f-41f6-3aaa-dfb35815f200/public" alt="Sublime's custom image"/>
<br>
<i>Invert Binary Tree</i>
</p>    

- Do DFS
- swap left and right nodes and return node

## Max height of B'Tree
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/5ea6da77-7e43-43e0-dd9d-e879ca0b1600/public" alt="Sublime's custom image"/>
<br>
<i>height is 3</i>
</p>  

- Do DFS
- base case at null node return 0
- then return max(1+height(left) & 1+height(right))

## Same Tree
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/e78fc10c-4692-471f-5261-61e9be4f3a00/public" alt="Sublime's custom image"/>
<br>
<i>They are same</i>
</p>   

- Do DFS on both root nodes
- base case return true if both tails are null
- if either is not null and one is null, return false
- if nodes are not equal return false;
- return on same(left) and same(right)

# Sub-tree 
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/2991a77a-9664-46ed-528d-019e392f7400/public" alt="Sublime's custom image"/>
<br>
<i>Sub tree is present</i>
</p>  
 
- Do DFS top down approach
- base case when you reach main's leaf and you still didnt find shit.. then return false
- if secondary is null return true
- call same tree on root, if yes return true
- return  isSubTree(left) || isSubTree(right)  

#Lowest Common Ancestor of BST
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/2080ee6a-3d27-4cd5-0db2-07672ead8200/public" alt="Sublime's custom image"/>
<br>
<i>LCA of 1 & 4 is 3, LCA of 3 & 1 is 3, LCA of 1 and 9 is 5</i>
</p>  

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

# Valid BST

