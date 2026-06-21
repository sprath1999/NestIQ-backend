package com.nestiq.resident.dto;

import com.nestiq.resident.entity.ComplaintCategory;
import com.nestiq.resident.entity.ComplaintStatus;
import java.time.LocalDateTime;

public class ComplaintResponse {

    private Long id;
    private Long residentId;
    private String residentName;
    private String flatNumber;
    private String title;
    private String description;
    private ComplaintCategory category;
    private ComplaintStatus status;
    private String assignedTo;
    private String resolutionNote;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters
    public Long getId() { return id; }
    public Long getResidentId() { return residentId; }
    public String getResidentName() { return residentName; }
    public String getFlatNumber() { return flatNumber; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ComplaintCategory getCategory() { return category; }
    public ComplaintStatus getStatus() { return status; }
    public String getAssignedTo() { return assignedTo; }
    public String getResolutionNote() { return resolutionNote; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(ComplaintCategory category) { this.category = category; }
    public void setStatus(ComplaintStatus status) { this.status = status; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setResolutionNote(String resolutionNote) { this.resolutionNote = resolutionNote; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long residentId;
        private String residentName;
        private String flatNumber;
        private String title;
        private String description;
        private ComplaintCategory category;
        private ComplaintStatus status;
        private String assignedTo;
        private String resolutionNote;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder category(ComplaintCategory category) { this.category = category; return this; }
        public Builder status(ComplaintStatus status) { this.status = status; return this; }
        public Builder assignedTo(String assignedTo) { this.assignedTo = assignedTo; return this; }
        public Builder resolutionNote(String resolutionNote) { this.resolutionNote = resolutionNote; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public ComplaintResponse build() {
            ComplaintResponse response = new ComplaintResponse();
            response.id = this.id;
            response.residentId = this.residentId;
            response.residentName = this.residentName;
            response.flatNumber = this.flatNumber;
            response.title = this.title;
            response.description = this.description;
            response.category = this.category;
            response.status = this.status;
            response.assignedTo = this.assignedTo;
            response.resolutionNote = this.resolutionNote;
            response.createdAt = this.createdAt;
            response.updatedAt = this.updatedAt;
            return response;
        }
    }
}