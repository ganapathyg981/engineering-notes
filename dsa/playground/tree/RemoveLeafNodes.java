package tree;

class RemoveLeafNodes {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return remove(root, target)?null:root;
    }

    public boolean remove(TreeNode root, int target) {
        if(root== null) {
            return false;
        }
        boolean left = remove(root.left, target);
        boolean right = remove(root.right,target);
        if(left) {
            root.left = null;
        }
        if(right) {
            root.right = null;
        }
        return root.left == null && root.right == null && root.val == target;
    }

    
}