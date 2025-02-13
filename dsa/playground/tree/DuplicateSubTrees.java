    package tree;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class DuplicateSubTrees {
        public static void main(String[] args) {
            // Construct the test case tree:
            //         1
            //        / \
            //       2   3
            //      /   / \
            //     4   2   4
            //        /
            //       4

            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(2);
            root.right.right = new TreeNode(4);
            root.right.left.left = new TreeNode(4);


            // Call the method
            List<TreeNode> duplicates = findDuplicateSubtrees(root);

            // Print the duplicate subtrees
            System.out.println("Duplicate Subtrees Roots:");
            for (TreeNode node : duplicates) {
                System.out.println(node.val);
            }
        }
        public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> result = new ArrayList<>();
            Map<String, Integer> freq = new HashMap<>();
            helper(root, freq, result);
            return result;
        }

        private static String helper(TreeNode root, Map<String, Integer> freq, List<TreeNode> result) {
            if(root == null) {
                return "#";
            }
            String left = helper(root.left, freq, result);
            String right = helper(root.right, freq, result);
            String combine = root.val+","+left+","+right;
            freq.put(combine, freq.getOrDefault(combine, 0) + 1);
            if(freq.get(combine) == 2) {
                System.out.println(combine);
                result.add(root);
            }
            return combine;
        }
    }
