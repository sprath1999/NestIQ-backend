package com.nestiq.visitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationClient {

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate;

    public NotificationClient() {
        this.restTemplate = new RestTemplate();
    }

    public void sendNotification(Long userId, String title, String message, String type, String data) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("userId", userId);
            body.put("title", title);
            body.put("message", message);
            body.put("type", type);
            body.put("data", data);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForObject(
                notificationServiceUrl + "/notifications/send",
                request,
                Object.class
            );
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}