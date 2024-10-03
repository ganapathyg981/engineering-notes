package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }

        // Get the maximum depth of the tree
        int maxDepth = getMaxDepth(root);

        // Use a queue to perform a level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Total width of the tree printout based on maxDepth
        int maxWidth = (int) Math.pow(2, maxDepth) - 1;

        // Start printing level by level
        int level = 0;
        while (level < maxDepth) {
            // Print nodes at current level
            List<TreeNode> nodesAtLevel = new ArrayList<>();
            for (int i = 0; i < Math.pow(2, level); i++) {
                TreeNode node = queue.poll();
                nodesAtLevel.add(node);
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    queue.add(null);  // Placeholder for null children
                    queue.add(null);
                }
            }
            printNodesAtLevel(nodesAtLevel, maxWidth / (int) Math.pow(2, level));
            printBranches(nodesAtLevel, maxWidth / (int) Math.pow(2, level));
            level++;
        }
    }

    private static void printNodesAtLevel(List<TreeNode> nodes, int gap) {
        // Print the nodes with calculated spaces
        for (int i = 0; i < nodes.size(); i++) {
            if (i == 0) {
                printSpaces(gap);
            } else {
                printSpaces(gap * 2 + 1);
            }
            if (nodes.get(i) == null) {
                System.out.print(" ");
            } else {
                System.out.print(nodes.get(i).val);
            }
        }
        System.out.println();
    }

    private static void printBranches(List<TreeNode> nodes, int gap) {
        // Print the branches ("/" and "\")
        for (int i = 0; i < nodes.size(); i++) {
            if (i == 0) {
                printSpaces(gap - 1);
            } else {
                printSpaces(gap * 2 - 1);
            }
            if (nodes.get(i) != null) {
                if (nodes.get(i).left != null) {
                    System.out.print("/");
                } else {
                    System.out.print(" ");
                }
                printSpaces(2);
                if (nodes.get(i).right != null) {
                    System.out.print("\\");
                } else {
                    System.out.print(" ");
                }
            } else {
                printSpaces(3);
            }
        }
        System.out.println();
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static int getMaxDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }
}