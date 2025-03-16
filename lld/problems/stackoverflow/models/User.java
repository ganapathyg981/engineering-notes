package stackoverflow.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {
    private String name;
    private String id;
    private String password;
    private Integer reputationScore;
    private Role role;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Comment> comments;

    public User (String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.reputationScore = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Question addQuestion(String title, String description, List<Tag> tags) {
        Question question = Question.builder()
                .id("1")
                .userId(id)
                .answers(new ArrayList<>())
                .description(description)
                .title(title)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .tags(tags)
                .votes(0)
                .comments(new ArrayList<>())
                .build();
        questions.add(question);
        return question;
    }

    public Answer addAnswer(String content, String questionId) {
        Answer answer = Answer.builder()
                .id(UUID.randomUUID().toString())
                .content(content)
                .userId(id)
                .isAccepted(false)
                .questionId(questionId)
                .comments(new ArrayList<>())
                .votes(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        answers.add(answer);
        return answer;
    }

    public Comment addComment(String comment) {
        Comment commentObject = Comment.builder()
                .comment(comment).id(UUID.randomUUID().toString())
                .userId(id)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        comments.add(commentObject);
        return commentObject;
    }

    public void updateReputationScore(Integer point) {
        this.setReputationScore(this.getReputationScore() + point);
    }

    public void removeAnswers(List<String> answerIds){
        for (int i = 0; i < answers.size(); i++) {
            if(answerIds.contains(answers.get(i).getId())) {
                answers.remove(i);
            }
        }
    }
    public void removeComments(List<String> commentIds){
        for (int i = 0; i < comments.size(); i++) {
            if(commentIds.contains(comments.get(i).getId())) {
                comments.remove(i);
            }
        }
    }

    public void removeQuestion(String questionId){
        for (int i = 0; i < questions.size(); i++) {
            if(questionId.equals(questions.get(i).getId())) {
                questions.remove(i);
            }
        }
    }

}
