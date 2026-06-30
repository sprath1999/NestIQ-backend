package com.nestiq.visitor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long residentId;

    @Column(nullable = false)
    private String flatNumber;

    @Column(nullable = false)
    private String residentName;

    @Column(nullable = false)
    private String sender;

    private String description;

    @Column(nullable = false)
    private boolean collected = false;

    private LocalDateTime collectedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime arrivedAt;

    @PrePersist
    protected void onCreate() {
        arrivedAt = LocalDateTime.now();
    }

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

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long residentId;
        private String flatNumber;
        private String residentName;
        private String sender;
        private String description;

        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder sender(String sender) { this.sender = sender; return this; }
        public Builder description(String description) { this.description = description; return this; }

        public Parcel build() {
            Parcel parcel = new Parcel();
            parcel.residentId = this.residentId;
            parcel.flatNumber = this.flatNumber;
            parcel.residentName = this.residentName;
            parcel.sender = this.sender;
            parcel.description = this.description;
            return parcel;
        }
    }
}