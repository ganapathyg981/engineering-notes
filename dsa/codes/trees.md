## DFS
```java
void dfs(Tree node){
    if(node == null) {
        return;
    }
    System.out.println(node.val);  // preorder
    dfs(node.left);
    dfs(node.right);
}
```
## BFS
```java
void bfs(Tree node){
    if(node == null) {
        return;
    }
    Queue<Integer> queue = new Queue();
    List<List<Integer>> out = new ArrayList();
    queue.add(node.val);
    while(!queue.isEmpty()) {
        List<Integer> list = new ArrayList();
        levelSize=queue.size;
        for(levelSize) {
            current = queue.poll();
            list.add(current.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        out.add(list);
    }
}
```


