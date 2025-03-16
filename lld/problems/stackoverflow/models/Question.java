package stackoverflow.models;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Question implements Votable, Commentable{
    private String id;
    private String userId;
    private String title;
    private String description;
    private List<Tag> tags;
    private List<Answer> answers;
    private List<Comment> comments;
    private Integer votes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Boolean answer(Answer answer) {
        return answers.add(answer);
    }

    @Override
    public void comment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void vote(VoteType voteType) {
        if(voteType.equals(VoteType.UP_VOTE)) {
            this.votes+=1;
        } else {
            this.votes-=1;
        }
    }

    @Override
    public List<String> getCommentIds() {
        List<String> commentIds = new ArrayList<>();
        for (Comment comment: comments) {
            Utils.dfs(comment, commentIds);
        }
        return commentIds;
    }

    public List<String> getAnswerIds() {
       return answers.stream().map(Answer::getId).toList();
    }
}
