# Trees
<p align="center" style="width:50vw">
  <img src="https://journaldev.nyc3.cdn.digitaloceanspaces.com/2020/08/fully-balanced-1024x512.png" alt="Sublime's custom image"/>
<br>
Binary Tree
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
Invert Binary Tree
</p>    

- Do DFS
- swap left and right nodes and return node

## Max height of B'Tree
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/5ea6da77-7e43-43e0-dd9d-e879ca0b1600/public" alt="Sublime's custom image"/>
<br>
height is 3
</p>  

- Do DFS
- base case at null node return 0
- then return max(1+height(left) & 1+height(right))

## Same Tree
<p align="center" style="width:50vw">
  <img src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/e78fc10c-4692-471f-5261-61e9be4f3a00/public" alt="Sublime's custom image"/>
<br>
They are same
</p>

