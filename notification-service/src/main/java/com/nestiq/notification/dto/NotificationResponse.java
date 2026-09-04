package com.nestiq.notification.dto;

import java.time.LocalDateTime;

public class NotificationResponse {

    private Long id;
    private Long userId;
    private String title;
    private String message;
    private String type;
    private boolean read;
    private String data;
    private LocalDateTime createdAt;

    // Getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public boolean isRead() { return read; }
    public String getData() { return data; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setTitle(String title) { this.title = title; }
    public void setMessage(String message) { this.message = message; }
    public void setType(String type) { this.type = type; }
    public void setRead(boolean read) { this.read = read; }
    public void setData(String data) { this.data = data; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long userId;
        private String title;
        private String message;
        private String type;
        private boolean read;
        private String data;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder read(boolean read) { this.read = read; return this; }
        public Builder data(String data) { this.data = data; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public NotificationResponse build() {
            NotificationResponse response = new NotificationResponse();
            response.id = this.id;
            response.userId = this.userId;
            response.title = this.title;
            response.message = this.message;
            response.type = this.type;
            response.read = this.read;
            response.data = this.data;
            response.createdAt = this.createdAt;
            return response;
        }
    }
}