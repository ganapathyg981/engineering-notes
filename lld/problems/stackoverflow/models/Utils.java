package stackoverflow.models;

import java.util.List;

public class Utils {
    public static void dfs(Comment comment, List<String> commentIds) {
        if(comment == null) {
            return;
        }
        commentIds.add(comment.getId());
        for (Comment childComment: comment.getComments()) {
            dfs(childComment, commentIds);
        }
    }
}
