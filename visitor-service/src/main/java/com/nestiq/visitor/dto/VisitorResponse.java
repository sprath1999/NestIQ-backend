package com.nestiq.visitor.dto;

import com.nestiq.visitor.entity.VisitorStatus;
import java.time.LocalDateTime;

public class VisitorResponse {

    private Long id;
    private String visitorName;
    private String visitorPhone;
    private String purpose;
    private Long residentId;
    private String flatNumber;
    private String residentName;
    private VisitorStatus status;
    private boolean preApproved;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private LocalDateTime expectedTime;
    private LocalDateTime createdAt;

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
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String visitorName;
        private String visitorPhone;
        private String purpose;
        private Long residentId;
        private String flatNumber;
        private String residentName;
        private VisitorStatus status;
        private boolean preApproved;
        private LocalDateTime entryTime;
        private LocalDateTime exitTime;
        private LocalDateTime expectedTime;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder visitorName(String visitorName) { this.visitorName = visitorName; return this; }
        public Builder visitorPhone(String visitorPhone) { this.visitorPhone = visitorPhone; return this; }
        public Builder purpose(String purpose) { this.purpose = purpose; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder status(VisitorStatus status) { this.status = status; return this; }
        public Builder preApproved(boolean preApproved) { this.preApproved = preApproved; return this; }
        public Builder entryTime(LocalDateTime entryTime) { this.entryTime = entryTime; return this; }
        public Builder exitTime(LocalDateTime exitTime) { this.exitTime = exitTime; return this; }
        public Builder expectedTime(LocalDateTime expectedTime) { this.expectedTime = expectedTime; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public VisitorResponse build() {
            VisitorResponse response = new VisitorResponse();
            response.id = this.id;
            response.visitorName = this.visitorName;
            response.visitorPhone = this.visitorPhone;
            response.purpose = this.purpose;
            response.residentId = this.residentId;
            response.flatNumber = this.flatNumber;
            response.residentName = this.residentName;
            response.status = this.status;
            response.preApproved = this.preApproved;
            response.entryTime = this.entryTime;
            response.exitTime = this.exitTime;
            response.expectedTime = this.expectedTime;
            response.createdAt = this.createdAt;
            return response;
        }
    }
}