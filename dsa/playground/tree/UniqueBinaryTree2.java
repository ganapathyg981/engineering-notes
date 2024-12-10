package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UniqueBinaryTree2 {
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        // Start with the full range of numbers from 1 to n
        return getBinaryTrees(1, n);
    }

    private static List<TreeNode> getBinaryTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();

        // Base case: if there are no numbers to construct the tree
        if (start > end) {
            allTrees.add(null);  // Return a list with null to signify no child
            return allTrees;
        }

        // Iterate over all numbers as root from start to end
        for (int i = start; i <= end; i++) {
            // Recursively generate all possible left and right subtrees
            List<TreeNode> leftTrees = getBinaryTrees(start, i - 1);
            List<TreeNode> rightTrees = getBinaryTrees(i + 1, end);

            // Combine each left subtree with each right subtree
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);  // Use i as the root
                    root.left = left;   // Set left subtree
                    root.right = right; // Set right subtree
                    allTrees.add(root); // Add this tree to the list
                }
            }
        }

        return allTrees;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9};
        TreeNode root = initTree(arr);
//        TreeNode.levelOrder(root);
        TreeNode.inorder(root);

    }

    public static TreeNode initTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode currentNode = queue.poll();

            // Assign left child
            if (i < arr.length && arr[i] != null) {
                currentNode.left = new TreeNode(arr[i]);
                queue.offer(currentNode.left);
            }
            i++;

            // Assign right child
            if (i < arr.length && arr[i] != null) {
                currentNode.right = new TreeNode(arr[i]);
                queue.offer(currentNode.right);
            }
            i++;
        }

        return root;
    }

}
