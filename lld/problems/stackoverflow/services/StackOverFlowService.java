package stackoverflow.services;

import lombok.Data;
import stackoverflow.models.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class StackOverFlowService {
    UserService userService;
    NotificationService notificationService;
    Map<String, Question> questionMap;
    Map<String, Answer> answerMap;
    Map<String, Comment> commentMap;
    static StackOverFlowService instance;

    private StackOverFlowService(UserService userService) {
        this.userService = userService;
        this.notificationService = NotificationService.getInstance(userService);
        questionMap = new ConcurrentHashMap<>();
        commentMap = new ConcurrentHashMap<>();
        answerMap = new ConcurrentHashMap<>();
    }

    public static synchronized StackOverFlowService getInstance(UserService userService) {
        if(instance == null) {
            instance = new StackOverFlowService(userService);
        }
        return instance;
    }

    public Boolean postQuestion(String userId, String title, String description, List<Tag> tags) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        Question question = user.addQuestion(title, description, tags);
        questionMap.put(question.getId(), question);
        return true;
    }

    public Boolean postAnswer(String userId, String content, String questionId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        Answer answer = user.addAnswer(content, questionId);
        boolean success = questionMap.get(questionId).answer(answer);

        if (success) {
            answerMap.put(answer.getId(), answer);
            // Automatically subscribe the answerer to the question
            notificationService.subscribeToQuestion(userId, questionId);
            // Notify question owner and subscribers
            notificationService.notifyNewAnswer(questionMap.get(questionId), answer);
        }

        return success;
    }

    public Boolean postComment(String comment, String id, CommentableType type, String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        Comment comment1 = user.addComment(comment);
        Commentable commentable = switch (type) {
            case ANSWER -> answerMap.get(id);
            case QUESTION -> questionMap.get(id);
            case COMMENT -> commentMap.get(id);
        };
        if (commentable == null) {
            return false;
        }

        commentable.comment(comment1);

        commentMap.put(comment1.getId(), comment1);
        // Automatically subscribe the commenter to the question
        String questionId = null;
        if (type == CommentableType.QUESTION) {
            questionId = id;
        } else if (type == CommentableType.ANSWER) {
            questionId = answerMap.get(id).getQuestionId();
        }

        if (questionId != null) {
            notificationService.subscribeToQuestion(userId, questionId);
        }

        // Notify the owner of the commented content
        notificationService.notifyNewComment(comment1, id, type);


        return true;
    }

    public Boolean vote(VoteType voteType, String id, VotableType type, String voterId) {
        Votable votable = switch (type) {
            case ANSWER -> answerMap.get(id);
            case QUESTION -> questionMap.get(id);
        };
        if (votable == null) {
            return false;
        }

        votable.vote(voteType);
        String userId = votable.getUserId();
        User user = userService.getUser(userId);
        user.setReputationScore(voteType.equals(VoteType.UP_VOTE) ? 10 : -2);

        // Determine questionId for notification
        String questionId = null;
        if (type == VotableType.QUESTION) {
            questionId = id;
        } else if (type == VotableType.ANSWER) {
            questionId = answerMap.get(id).getQuestionId();
        }

        // Notify about the vote
        notificationService.notifyVote(id, type, voteType, questionId);

        return true;
    }

    public Boolean acceptAnswer(String answerId, String userId) {
        Answer answer = answerMap.get(answerId);
        if (answer == null) {
            return false;
        }

        String questionId = answer.getQuestionId();
        if (!questionMap.get(questionId).getUserId().equals(userId)) {
            return false;
        }

        String answerUserId = answer.getUserId();
        User user = userService.getUser(answerUserId);
        user.updateReputationScore(15);

        // Mark answer as accepted in the model
        answer.setIsAccepted(true);

        // Notify the answer author
        notificationService.notifyAnswerAccepted(answer, questionId);

        return true;
    }

    public boolean updateAnswer(String userId, String content, String answerId) {
        Optional<User> userOpt = Optional.ofNullable(userService.getUser(userId));
        Optional<Answer> answerOpt = Optional.ofNullable(answerMap.get(answerId));
        if (userOpt.isEmpty() || answerOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        Answer answer = answerOpt.get();
        if (!canUpdateAnswer(user, answer)) {
            return false;
        }

        answer.setContent(content);
        answer.setUpdatedAt(LocalDateTime.now());

        // Notify subscribers
        notificationService.notifyAnswerUpdated(answer, answer.getQuestionId());

        return true;
    }

    public boolean updateQuestion(String userId, String title, String description, List<Tag> tags, String questionId) {
        Optional<User> userOpt = Optional.ofNullable(userService.getUser(userId));
        Optional<Question> questionOpt = Optional.ofNullable(questionMap.get(questionId));

        if (userOpt.isEmpty() || questionOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        Question question = questionOpt.get();

        if (!canUpdateQuestion(user, question)) {
            return false;
        }

        updateQuestionFields(question, title, description, tags);

        // Notify subscribers
        notificationService.notifyQuestionUpdated(question);

        return true;
    }

    public boolean deleteAnswer(String userId, String answerId) {
        User user = userService.getUser(userId);
        Answer answer = answerMap.get(answerId);

        if (user == null || answer == null) {
            return false;
        }

        List<String> commentIdsToBeDeleted = answer.getCommentIds();
        user.removeAnswers(List.of(answerId));
        user.removeComments(commentIdsToBeDeleted);

        // Remove from maps
        answerMap.remove(answerId);
        removeComments(commentIdsToBeDeleted);

        return true;
    }

    public boolean deleteQuestion(String userId, String questionId) {
        User user = userService.getUser(userId);
        Question question = questionMap.get(questionId);

        if (user == null || question == null) {
            return false;
        }

        user.removeQuestion(questionId);

        List<String> answersToBeDeleted = question.getAnswerIds();
        List<String> commentIdsToBeDeleted = question.getCommentIds();

        // Delete associated answers
        answersToBeDeleted.forEach(answerId -> deleteAnswer(userId, answerId));

        // Remove comments directly linked to the question
        removeComments(commentIdsToBeDeleted);

        // Remove the question itself
        questionMap.remove(questionId);

        return true;
    }

    // Subscription management methods
    public boolean subscribeToTag(String userId, String tagName) {
        return notificationService.subscribeToTag(userId, tagName);
    }

    public boolean unsubscribeFromTag(String userId, String tagName) {
        return notificationService.unsubscribeFromTag(userId, tagName);
    }

    public boolean subscribeToQuestion(String userId, String questionId) {
        return notificationService.subscribeToQuestion(userId, questionId);
    }

    public boolean unsubscribeFromQuestion(String userId, String questionId) {
        return notificationService.unsubscribeFromQuestion(userId, questionId);
    }

    // Get user notifications
    public List<NotificationService.Notification> getUserNotifications(String userId) {
        return notificationService.getUserNotifications(userId);
    }

    // Mark notification as read
    public boolean markNotificationAsRead(String userId, String notificationId) {
        return notificationService.markNotificationAsRead(userId, notificationId);
    }

    // Mark all notifications as read
    public boolean markAllNotificationsAsRead(String userId) {
        return notificationService.markAllNotificationsAsRead(userId);
    }

    private void removeComments(List<String> commentIds) {
        commentIds.forEach(commentMap::remove);
    }

    private boolean canUpdateAnswer(User user, Answer answer) {
        return answer.getVotes() == 0 &&
                (user.getRole() == Role.ADMIN || answer.getUserId().equals(user.getId()));
    }

    private boolean canUpdateQuestion(User user, Question question) {
        return question.getVotes() == 0 &&
                (user.getRole() == Role.ADMIN || question.getUserId().equals(user.getId()));
    }

    private void updateQuestionFields(Question question, String title, String description, List<Tag> tags) {
        Optional.ofNullable(title).ifPresent(question::setTitle);
        Optional.ofNullable(description).ifPresent(question::setDescription);
        Optional.ofNullable(tags).ifPresent(question::setTags);
        question.setUpdatedAt(LocalDateTime.now());
    }
}