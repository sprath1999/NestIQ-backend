package com.nestiq.notification.service;

import com.nestiq.notification.dto.NotificationRequest;
import com.nestiq.notification.dto.NotificationResponse;
import com.nestiq.notification.entity.Notification;
import com.nestiq.notification.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository,
                               SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public NotificationResponse sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .message(request.getMessage())
                .type(request.getType())
                .data(request.getData())
                .build();

        notificationRepository.save(notification);

        NotificationResponse response = mapToResponse(notification);

        System.out.println("Sending WebSocket to userId: " + request.getUserId());
        
      
        
        messagingTemplate.convertAndSend(
                "/topic/notifications-" + request.getUserId(),
                response
        );

        
        System.out.println("WebSocket message sent: " + request.getUserId());

        return response;
    }
 
    public List<NotificationResponse> getMyNotifications(Long userId) {
        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<NotificationResponse> getUnreadNotifications(Long userId) {
        return notificationRepository
                .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndReadFalse(userId);
    }

    @Transactional
    public void markAllAsRead(Long userId) {
        notificationRepository.markAllAsRead(userId);
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .read(notification.isRead())
                .data(notification.getData())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}