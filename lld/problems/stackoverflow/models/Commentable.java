package stackoverflow.models;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();
    void comment(Comment comment);
    List<String> getCommentIds();
}
