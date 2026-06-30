package com.nestiq.visitor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitors")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String visitorName;

    @Column(nullable = false)
    private String visitorPhone;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private Long residentId;

    @Column(nullable = false)
    private String flatNumber;

    @Column(nullable = false)
    private String residentName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitorStatus status;

    private boolean preApproved;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private LocalDateTime expectedTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = VisitorStatus.EXPECTED;
    }

    // Getters
    public Long getId() { return id; }
    public String getVisitorName() { return visitorName; }
    public String getVisitorPhone() { return visitorPhone; }
    public String getPurpose() { return purpose; }
    public Long getResidentId() { return residentId; }
    public String getFlatNumber() { return flatNumber; }
    public String getResidentName() { return residentName; }
    public VisitorStatus getStatus() { return status; }
    public boolean isPreApproved() { return preApproved; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public LocalDateTime getExpectedTime() { return expectedTime; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public void setVisitorPhone(String visitorPhone) { this.visitorPhone = visitorPhone; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setStatus(VisitorStatus status) { this.status = status; }
    public void setPreApproved(boolean preApproved) { this.preApproved = preApproved; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public void setExpectedTime(LocalDateTime expectedTime) { this.expectedTime = expectedTime; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String visitorName;
        private String visitorPhone;
        private String purpose;
        private Long residentId;
        private String flatNumber;
        private String residentName;
        private VisitorStatus status;
        private boolean preApproved;
        private LocalDateTime expectedTime;

        public Builder visitorName(String visitorName) { this.visitorName = visitorName; return this; }
        public Builder visitorPhone(String visitorPhone) { this.visitorPhone = visitorPhone; return this; }
        public Builder purpose(String purpose) { this.purpose = purpose; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder status(VisitorStatus status) { this.status = status; return this; }
        public Builder preApproved(boolean preApproved) { this.preApproved = preApproved; return this; }
        public Builder expectedTime(LocalDateTime expectedTime) { this.expectedTime = expectedTime; return this; }

        public Visitor build() {
            Visitor visitor = new Visitor();
            visitor.visitorName = this.visitorName;
            visitor.visitorPhone = this.visitorPhone;
            visitor.purpose = this.purpose;
            visitor.residentId = this.residentId;
            visitor.flatNumber = this.flatNumber;
            visitor.residentName = this.residentName;
            visitor.status = this.status;
            visitor.preApproved = this.preApproved;
            visitor.expectedTime = this.expectedTime;
            return visitor;
        }
    }
}