package tree;

public class SumLeaf {
    public static void main(String[] args) {

        // Test Case 1: Simple tree with one path
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test Case 1: " + sumNumbers(root1)); // Expected output: 25 (12 + 13)

        // Test Case 2: Tree with multiple paths
        TreeNode root2 = new TreeNode(4,
                            new TreeNode(9,
                                new TreeNode(5),
                                new TreeNode(1)),
                            new TreeNode(0));
        System.out.println("Test Case 2: " + sumNumbers(root2)); // Expected output: 1026 (495 + 491 + 40)

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(7);
        System.out.println("Test Case 3: " + sumNumbers(root3)); // Expected output: 7

        // Test Case 4: Null tree
        TreeNode root4 = null;
        System.out.println("Test Case 4: " + sumNumbers(root4)); // Expected output: 0
    }

    private static int sumNumbers(TreeNode root1) {
        return helper(root1, 0);
    }

    private static int helper(TreeNode root1, int sum) {

        if(root1 == null) {
            return 0;
        }
        sum = sum * 10 + root1.val;
        if(root1.left == null && root1.right==null) {
            return sum;
        }
         return helper(root1.left,sum )+helper(root1.right, sum);

    }
}