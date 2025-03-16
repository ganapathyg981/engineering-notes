package stackoverflow.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Answer implements Votable, Commentable{
    private String id;
    private String userId;
    private String questionId;
    private String content;
    private List<Comment> comments;
    private Integer votes;
    private Boolean isAccepted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    @Override
    public void vote(VoteType voteType) {
        if(voteType.equals(VoteType.UP_VOTE)) {
            this.votes+=1;
        } else {
            this.votes-=1;
        }
    }


}
