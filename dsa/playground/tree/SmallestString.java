package tree;

public class SmallestString {
    public static void main(String[] args) {

        // Test Case 1: Simple treˀ
        TreeNode root1 = new TreeNode(0,
                new TreeNode(1),
                new TreeNode(2));
        System.out.println("Test Case 1: " + smallestFromLeaf(root1)); // Expected: "ab" or "ac" (whichever is smaller)

        // Test Case 2: Tree with multiple leaves
        TreeNode root2 = new TreeNode(25,
                new TreeNode(1,
                        new TreeNode(1),
                        new TreeNode(3)),
                new TreeNode(3,
                        null,
                        new TreeNode(4)));
        System.out.println("Test Case 2: " + smallestFromLeaf(root2)); // Expected: "adz"

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(3);
        System.out.println("Test Case 3: " + smallestFromLeaf(root3)); // Expected: "d"

        // Test Case 4: Tree with a deeper path
        TreeNode root4 = new TreeNode(2,
                new TreeNode(2,
                        new TreeNode(1,
                                new TreeNode(0), null),
                        null),
                null);
        System.out.println("Test Case 4: " + smallestFromLeaf(root4)); // Expected: "azba"

        // Test Case 5: Null tree
        TreeNode root5 = null;
        System.out.println("Test Case 5: " + smallestFromLeaf(root5)); // Expected: ""
    }

    public static String smallestFromLeaf(TreeNode root1) {
        return helper(root1, "");
    }

    private static String helper(TreeNode treeNode, String path) {
        if (treeNode == null) {
            return null;
        }

        // Prepend current character to the path
        path = (char) ('a' + treeNode.val) + path;

        // If it's a leaf node, return the path
        if (treeNode.left == null && treeNode.right == null) {
            return path;
        }

        // Recur for left and right subtrees
        String left = helper(treeNode.left, path);
        String right = helper(treeNode.right, path);

        // Handle null cases
        if (left == null) return right;
        if (right == null) return left;

        // Return the smaller of the two paths
        return left.compareTo(right) < 0 ? left : right;
    }
}