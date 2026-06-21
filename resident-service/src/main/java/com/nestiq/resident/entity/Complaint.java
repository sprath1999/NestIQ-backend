package com.nestiq.resident.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long residentId;

    @Column(nullable = false)
    private String residentName;

    @Column(nullable = false)
    private String flatNumber;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status;

    private String assignedTo;

    private String resolutionNote;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = ComplaintStatus.OPEN;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long residentId;
        private String residentName;
        private String flatNumber;
        private String title;
        private String description;
        private ComplaintCategory category;
        private ComplaintStatus status;

        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder category(ComplaintCategory category) { this.category = category; return this; }
        public Builder status(ComplaintStatus status) { this.status = status; return this; }

        public Complaint build() {
            Complaint complaint = new Complaint();
            complaint.residentId = this.residentId;
            complaint.residentName = this.residentName;
            complaint.flatNumber = this.flatNumber;
            complaint.title = this.title;
            complaint.description = this.description;
            complaint.category = this.category;
            complaint.status = this.status;
            return complaint;
        }
    }
}