package tree;


import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static int getMaxDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }

    public static void inorder(TreeNode node) {
        if(node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.val);
        inorder(node.right);

    }

        // BFS method to print each level in a new line
        public  static  void levelOrder(TreeNode root) {
            if (root == null) return;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();  // Number of nodes at the current level

                // Using another while loop instead of for loop
                int count = 0;
                while (count < levelSize) {
                    TreeNode currentNode = queue.poll();
                    System.out.print(currentNode.val + " ");

                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                    count++;
                }
                System.out.println();  // Move to the next line after printing one level
            }
        }
}