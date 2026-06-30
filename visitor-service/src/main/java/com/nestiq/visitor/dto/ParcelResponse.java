package com.nestiq.visitor.dto;

import java.time.LocalDateTime;

public class ParcelResponse {

    private Long id;
    private Long residentId;
    private String flatNumber;
    private String residentName;
    private String sender;
    private String description;
    private boolean collected;
    private LocalDateTime collectedAt;
    private LocalDateTime arrivedAt;

    // Getters
    public Long getId() { return id; }
    public Long getResidentId() { return residentId; }
    public String getFlatNumber() { return flatNumber; }
    public String getResidentName() { return residentName; }
    public String getSender() { return sender; }
    public String getDescription() { return description; }
    public boolean isCollected() { return collected; }
    public LocalDateTime getCollectedAt() { return collectedAt; }
    public LocalDateTime getArrivedAt() { return arrivedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setSender(String sender) { this.sender = sender; }
    public void setDescription(String description) { this.description = description; }
    public void setCollected(boolean collected) { this.collected = collected; }
    public void setCollectedAt(LocalDateTime collectedAt) { this.collectedAt = collectedAt; }
    public void setArrivedAt(LocalDateTime arrivedAt) { this.arrivedAt = arrivedAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long residentId;
        private String flatNumber;
        private String residentName;
        private String sender;
        private String description;
        private boolean collected;
        private LocalDateTime collectedAt;
        private LocalDateTime arrivedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder sender(String sender) { this.sender = sender; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder collected(boolean collected) { this.collected = collected; return this; }
        public Builder collectedAt(LocalDateTime collectedAt) { this.collectedAt = collectedAt; return this; }
        public Builder arrivedAt(LocalDateTime arrivedAt) { this.arrivedAt = arrivedAt; return this; }

        public ParcelResponse build() {
            ParcelResponse response = new ParcelResponse();
            response.id = this.id;
            response.residentId = this.residentId;
            response.flatNumber = this.flatNumber;
            response.residentName = this.residentName;
            response.sender = this.sender;
            response.description = this.description;
            response.collected = this.collected;
            response.collectedAt = this.collectedAt;
            response.arrivedAt = this.arrivedAt;
            return response;
        }
    }
}