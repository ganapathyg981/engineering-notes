package tree;

import java.util.Objects;

public class TreeToString {
    public String tree2str(TreeNode root) {
        if(root== null) {
            return "";
        }
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        if(right.equals("") && !Objects.equals(left, "")) {
            return root.val+"("+left+")";
        }
        if("".equals(right) && "".equals(left)) {
            return String.valueOf(root.val);
        }
        return root.val+"("+left+")"+"("+right+")";
    }
}
