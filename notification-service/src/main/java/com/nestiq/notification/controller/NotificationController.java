package com.nestiq.notification.controller;

import com.nestiq.notification.dto.NotificationRequest;
import com.nestiq.notification.dto.NotificationResponse;
import com.nestiq.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Internal API — called by other services to send notification
    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(
            @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    // Get all notifications for logged in user
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(
            Authentication authentication) {
        Long userId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(notificationService.getMyNotifications(userId));
    }

    // Get unread notifications
    @GetMapping("/unread")
    public ResponseEntity<List<NotificationResponse>> getUnreadNotifications(
            Authentication authentication) {
        Long userId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    // Get unread count
    @GetMapping("/unread/count")
    public ResponseEntity<Long> getUnreadCount(Authentication authentication) {
        Long userId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(notificationService.getUnreadCount(userId));
    }

    // Mark all as read
    @PutMapping("/read-all")
    public ResponseEntity<String> markAllAsRead(Authentication authentication) {
        Long userId = ((Number) authentication.getCredentials()).longValue();
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok("All notifications marked as read");
    }

    // Mark single notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok("Notification marked as read");
    }
}