package tree;

import java.util.ArrayList;
import java.util.List;

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
        List<TreeNode> treeNodes = generateTrees(3);

        treeNodes.forEach(treeNode -> TreeNode.printTree(treeNode));

    }

}
