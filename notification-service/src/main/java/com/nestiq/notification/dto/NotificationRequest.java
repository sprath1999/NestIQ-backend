package com.nestiq.notification.dto;

public class NotificationRequest {

    private Long userId;
    private String title;
    private String message;
    private String type;
    private String data;

    // Getters
    public Long getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public String getData() { return data; }

    // Setters
    public void setUserId(Long userId) { this.userId = userId; }
    public void setTitle(String title) { this.title = title; }
    public void setMessage(String message) { this.message = message; }
    public void setType(String type) { this.type = type; }
    public void setData(String data) { this.data = data; }
}