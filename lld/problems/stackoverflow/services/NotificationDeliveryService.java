package stackoverflow.services;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NotificationDeliveryService {
    private static NotificationDeliveryService instance;
    private final Map<String, UserNotificationPreferences> userPreferences;
    
    private NotificationDeliveryService() {
        this.userPreferences = new HashMap<>();
    }
    
    public static synchronized NotificationDeliveryService getInstance() {
        if (instance == null) {
            instance = new NotificationDeliveryService();
        }
        return instance;
    }
    
    // Set user notification preferences
    public void setUserPreferences(String userId, UserNotificationPreferences preferences) {
        userPreferences.put(userId, preferences);
    }
    
    // Get user notification preferences
    public UserNotificationPreferences getUserPreferences(String userId) {
        return userPreferences.getOrDefault(userId, new UserNotificationPreferences());
    }
    
    // Send notification based on user preferences
    public void sendNotification(String userId, NotificationService.Notification notification) {
        UserNotificationPreferences preferences = getUserPreferences(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = notification.getCreatedAt().format(formatter);
        
        // Log the notification for system tracking
        logNotification(userId, notification, timestamp);
        
        // Check if email notifications are enabled for this notification type
        if (preferences.isEmailEnabled(notification.getType())) {
            sendEmailNotification(userId, notification, timestamp);
        }
        
        // Check if SMS notifications are enabled for this notification type
        if (preferences.isSmsEnabled(notification.getType())) {
            sendSmsNotification(userId, notification, timestamp);
        }
    }
    
    // Log notification to system log
    private void logNotification(String userId, NotificationService.Notification notification, String timestamp) {
        log.info("[NOTIFICATION LOG] User: {}, Time: {}, Type: {}, Title: {}, Content: {}",
                userId, timestamp, notification.getType(), notification.getTitle(), notification.getContent());
    }
    
    // Send email notification (simulation)
    private void sendEmailNotification(String userId, NotificationService.Notification notification, String timestamp) {
        // In a real implementation, this would connect to an email service
        log.info("[EMAIL NOTIFICATION] To User: {}, Time: {}, Subject: {}, Body: {}",
                userId, timestamp, notification.getTitle(), 
                notification.getContent() + "\n\nClick here to view: http://stackoverflow-clone.com/questions/" + notification.getQuestionId());
    }
    
    // Send SMS notification (simulation)
    private void sendSmsNotification(String userId, NotificationService.Notification notification, String timestamp) {
        // In a real implementation, this would connect to an SMS service
        String smsContent = notification.getTitle() + " - " + truncateContent(notification.getContent(), 100);
        log.info("[SMS NOTIFICATION] To User: {}, Time: {}, Message: {}", 
                userId, timestamp, smsContent);
    }
    
    // Helper method to truncate content for SMS
    private String truncateContent(String content, int maxLength) {
        if (content.length() <= maxLength) {
            return content;
        }
        return content.substring(0, maxLength - 3) + "...";
    }
    
    // Class to store user notification preferences
    @Data
    public static class UserNotificationPreferences {
        private Map<NotificationService.NotificationType, NotificationChannel> preferences;
        
        public UserNotificationPreferences() {
            preferences = new HashMap<>();
            // Default preferences: all notifications go to email only
            for (NotificationService.NotificationType type : NotificationService.NotificationType.values()) {
                preferences.put(type, NotificationChannel.EMAIL);
            }
        }
        
        public void setPreference(NotificationService.NotificationType type, NotificationChannel channel) {
            preferences.put(type, channel);
        }
        
        public NotificationChannel getPreference(NotificationService.NotificationType type) {
            return preferences.getOrDefault(type, NotificationChannel.EMAIL);
        }
        
        public boolean isEmailEnabled(NotificationService.NotificationType type) {
            NotificationChannel channel = getPreference(type);
            return channel == NotificationChannel.EMAIL || channel == NotificationChannel.BOTH;
        }
        
        public boolean isSmsEnabled(NotificationService.NotificationType type) {
            NotificationChannel channel = getPreference(type);
            return channel == NotificationChannel.SMS || channel == NotificationChannel.BOTH;
        }
    }
    
    // Enum for notification channels
    public enum NotificationChannel {
        EMAIL,
        SMS,
        BOTH,
        NONE
    }
}