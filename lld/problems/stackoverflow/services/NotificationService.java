package stackoverflow.services;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import stackoverflow.models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NotificationService {
    private static NotificationService instance;
    private final Map<String, List<Notification>> userNotifications;
    private final Map<String, Set<String>> tagSubscriptions;
    private final Map<String, Set<String>> questionSubscriptions;
    private final UserService userService;
    private final NotificationDeliveryService deliveryService;

    private NotificationService(UserService userService) {
        this.userService = userService;
        this.userNotifications = new ConcurrentHashMap<>();
        this.tagSubscriptions = new ConcurrentHashMap<>();
        this.questionSubscriptions = new ConcurrentHashMap<>();
        this.deliveryService = NotificationDeliveryService.getInstance();
    }

    public static synchronized NotificationService getInstance(UserService userService) {
        if(instance == null) {
            instance = new NotificationService(userService);
        }
        return instance;
    }

    // Subscribe to a tag
    public boolean subscribeToTag(String userId, String tagName) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        
        tagSubscriptions.computeIfAbsent(tagName, k -> new HashSet<>()).add(userId);
        return true;
    }

    // Unsubscribe from a tag
    public boolean unsubscribeFromTag(String userId, String tagName) {
        User user = userService.getUser(userId);
        if (user == null || !tagSubscriptions.containsKey(tagName)) {
            return false;
        }
        
        tagSubscriptions.get(tagName).remove(userId);
        return true;
    }

    // Subscribe to a question
    public boolean subscribeToQuestion(String userId, String questionId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        
        questionSubscriptions.computeIfAbsent(questionId, k -> new HashSet<>()).add(userId);
        return true;
    }

    // Unsubscribe from a question
    public boolean unsubscribeFromQuestion(String userId, String questionId) {
        User user = userService.getUser(userId);
        if (user == null || !questionSubscriptions.containsKey(questionId)) {
            return false;
        }
        
        questionSubscriptions.get(questionId).remove(userId);
        return true;
    }

    // Get user notifications
    public List<Notification> getUserNotifications(String userId) {
        return userNotifications.getOrDefault(userId, new ArrayList<>());
    }

    // Mark notification as read
    public boolean markNotificationAsRead(String userId, String notificationId) {
        List<Notification> notifications = userNotifications.get(userId);
        if (notifications == null) {
            return false;
        }
        
        for (Notification notification : notifications) {
            if (notification.getId().equals(notificationId)) {
                notification.setRead(true);
                return true;
            }
        }
        return false;
    }

    // Mark all notifications as read
    public boolean markAllNotificationsAsRead(String userId) {
        List<Notification> notifications = userNotifications.get(userId);
        if (notifications == null) {
            return false;
        }
        
        notifications.forEach(notification -> notification.setRead(true));
        return true;
    }

    // Set user notification preferences
    public void setUserNotificationPreferences(String userId, 
                                              NotificationDeliveryService.UserNotificationPreferences preferences) {
        deliveryService.setUserPreferences(userId, preferences);
    }

    // Get user notification preferences
    public NotificationDeliveryService.UserNotificationPreferences getUserNotificationPreferences(String userId) {
        return deliveryService.getUserPreferences(userId);
    }

    // Notify when a new answer is posted to a question
    public void notifyNewAnswer(Question question, Answer answer) {
        // Notify question owner
        String questionOwnerId = question.getUserId();
        Notification notification = new Notification(
            "New answer to your question: " + question.getTitle(),
            "Someone has posted a new answer to your question",
            NotificationType.NEW_ANSWER,
            answer.getId(),
            question.getId());
            
        addNotification(questionOwnerId, notification);

        // Notify subscribers to the question
        notifyQuestionSubscribers(question.getId(), 
                                "New answer to a question you're following",
                                "A new answer has been posted to a question you're following: " + question.getTitle(),
                                NotificationType.NEW_ANSWER,
                                answer.getId());

        // Notify tag subscribers
        notifyTagSubscribers(question.getTags(), 
                            "New activity on a tag you follow",
                            "A new answer has been posted to question with tags you follow: " + question.getTitle(),
                            NotificationType.NEW_ANSWER,
                            answer.getId(),
                            question.getId());
    }

    // Notify when a comment is posted
    public void notifyNewComment(Comment comment, String parentId, CommentableType type) {
        String ownerId = getOwnerIdByCommentableType(parentId, type);
        if (ownerId == null) {
            return;
        }

        String itemType = type.toString().toLowerCase();
        String questionId = getQuestionIdByCommentableType(parentId, type);
        
        // Notify content owner
        Notification notification = new Notification(
            "New comment on your " + itemType,
            "Someone has commented on your " + itemType,
            NotificationType.NEW_COMMENT,
            comment.getId(),
            questionId);
            
        addNotification(ownerId, notification);

        // Notify question subscribers if applicable
        if (questionId != null) {
            notifyQuestionSubscribers(questionId,
                                    "New comment on a question you're following",
                                    "A new comment has been added to a discussion you're following",
                                    NotificationType.NEW_COMMENT,
                                    comment.getId());
        }
    }

    // Notify on vote
    public void notifyVote(String contentId, VotableType type, VoteType voteType, String questionId) {
        String ownerId = getOwnerIdByVotableType(contentId, type);
        if (ownerId == null) {
            return;
        }

        String voteDirection = voteType == VoteType.UP_VOTE ? "upvoted" : "downvoted";
        String itemType = type.toString().toLowerCase();
        
        Notification notification = new Notification(
            "Your " + itemType + " was " + voteDirection,
            "Someone has " + voteDirection + " your " + itemType,
            voteType == VoteType.UP_VOTE ? NotificationType.UPVOTE : NotificationType.DOWNVOTE,
            contentId,
            questionId);
            
        addNotification(ownerId, notification);
        
        // Notify question subscribers
        if (questionId != null) {
            notifyQuestionSubscribers(questionId,
                                    "New vote on a question you're following",
                                    "A " + itemType + " on a question you're following has received a " + voteDirection,
                                    voteType == VoteType.UP_VOTE ? NotificationType.UPVOTE : NotificationType.DOWNVOTE,
                                    contentId);
        }
    }

    // Notify when an answer is accepted
    public void notifyAnswerAccepted(Answer answer, String questionId) {
        Notification notification = new Notification(
            "Your answer was accepted",
            "Your answer has been marked as accepted",
            NotificationType.ANSWER_ACCEPTED,
            answer.getId(),
            questionId);
            
        addNotification(answer.getUserId(), notification);
        
        // Notify question subscribers
        notifyQuestionSubscribers(questionId,
                                "Answer accepted on a question you're following",
                                "An answer has been accepted on a question you're following",
                                NotificationType.ANSWER_ACCEPTED,
                                answer.getId());
    }

    // Notify when a question is updated
    public void notifyQuestionUpdated(Question question) {
        notifyQuestionSubscribers(question.getId(),
                                "Question updated",
                                "A question you're following has been updated: " + question.getTitle(),
                                NotificationType.CONTENT_UPDATED,
                                question.getId());
        
        // Notify tag subscribers
        notifyTagSubscribers(question.getTags(),
                            "Update on a tag you follow",
                            "A question with tags you follow has been updated: " + question.getTitle(),
                            NotificationType.CONTENT_UPDATED,
                            question.getId(),
                            question.getId());
    }

    // Notify when an answer is updated
    public void notifyAnswerUpdated(Answer answer, String questionId) {
        notifyQuestionSubscribers(questionId,
                                "Answer updated",
                                "An answer to a question you're following has been updated",
                                NotificationType.CONTENT_UPDATED,
                                answer.getId());
    }

    // Helper methods
    private void addNotification(String userId, Notification notification) {
        if (userId == null) {
            return;
        }
        
        // Store notification in the internal system
        userNotifications.computeIfAbsent(userId, k -> new ArrayList<>()).add(notification);
        
        // Send notification through delivery service
        deliveryService.sendNotification(userId, notification);
        
        log.info("Notification added for user {}: {}", userId, notification.getTitle());
    }

    private void notifyQuestionSubscribers(String questionId, String title, String content, 
                                          NotificationType type, String contentId) {
        if (questionId == null || !questionSubscriptions.containsKey(questionId)) {
            return;
        }

        Set<String> subscribers = questionSubscriptions.get(questionId);
        
        for (String userId : subscribers) {
            Notification notification = new Notification(title, content, type, contentId, questionId);
            addNotification(userId, notification);
        }
    }

    private void notifyTagSubscribers(List<Tag> tags, String title, String content, 
                                     NotificationType type, String contentId, String questionId) {
        if (tags == null || tags.isEmpty()) {
            return;
        }

        Set<String> notifiedUsers = new HashSet<>();
        
        for (Tag tag : tags) {
            Set<String> subscribers = tagSubscriptions.getOrDefault(tag.getName(), new HashSet<>());
            for (String userId : subscribers) {
                if (!notifiedUsers.contains(userId)) {
                    Notification notification = new Notification(title, content, type, contentId, questionId);
                    addNotification(userId, notification);
                    notifiedUsers.add(userId);
                }
            }
        }
    }

    private String getOwnerIdByCommentableType(String id, CommentableType type) {
        switch (type) {
            case QUESTION:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getQuestionMap().get(id))
                        .map(Question::getUserId)
                        .orElse(null);
            case ANSWER:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getAnswerMap().get(id))
                        .map(Answer::getUserId)
                        .orElse(null);
            case COMMENT:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getCommentMap().get(id))
                        .map(Comment::getUserId)
                        .orElse(null);
            default:
                return null;
        }
    }

    private String getOwnerIdByVotableType(String id, VotableType type) {
        switch (type) {
            case QUESTION:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getQuestionMap().get(id))
                        .map(Question::getUserId)
                        .orElse(null);
            case ANSWER:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getAnswerMap().get(id))
                        .map(Answer::getUserId)
                        .orElse(null);
            default:
                return null;
        }
    }

    private String getQuestionIdByCommentableType(String id, CommentableType type) {
        switch (type) {
            case QUESTION:
                return id;
            case ANSWER:
                return Optional.ofNullable(StackOverFlowService.getInstance(userService)
                        .getAnswerMap().get(id))
                        .map(Answer::getQuestionId)
                        .orElse(null);
            case COMMENT:
                // This is more complex as we need to find what the comment is attached to
                // For simplicity, we'll return null, but a full implementation would trace back to the question
                return null;
            default:
                return null;
        }
    }

    // Notification class
    @Data
    public static class Notification {
        private final String id;
        private final String title;
        private final String content;
        private final NotificationType type;
        private final String contentId;
        private final String questionId;
        private final LocalDateTime createdAt;
        private boolean read;

        public Notification(String title, String content, NotificationType type, String contentId, String questionId) {
            this.id = UUID.randomUUID().toString();
            this.title = title;
            this.content = content;
            this.type = type;
            this.contentId = contentId;
            this.questionId = questionId;
            this.createdAt = LocalDateTime.now();
            this.read = false;
        }
    }

    // Notification types enum
    public enum NotificationType {
        NEW_ANSWER,
        NEW_COMMENT,
        UPVOTE,
        DOWNVOTE,
        ANSWER_ACCEPTED,
        CONTENT_UPDATED
    }
}