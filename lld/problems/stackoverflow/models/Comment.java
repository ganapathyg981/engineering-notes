package stackoverflow.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Comment implements Commentable{
    private String id;
    private String userId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Comment> comments;

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void comment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<String> getCommentIds() {
        List<String> commentIds = new ArrayList<>();
        for (Comment comment: comments) {
            Utils.dfs(comment, commentIds);
        }
        return commentIds;
    }
}
