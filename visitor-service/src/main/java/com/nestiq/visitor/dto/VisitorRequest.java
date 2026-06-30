package com.nestiq.visitor.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class VisitorRequest {

    @NotBlank(message = "Visitor name is required")
    private String visitorName;

    @NotBlank(message = "Visitor phone is required")
    private String visitorPhone;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    private boolean preApproved;
    private LocalDateTime expectedTime;
    private String flatNumber;
    private String residentName;
    private Long residentId;

    // Getters
    public String getVisitorName() { return visitorName; }
    public String getVisitorPhone() { return visitorPhone; }
    public String getPurpose() { return purpose; }
    public boolean isPreApproved() { return preApproved; }
    public LocalDateTime getExpectedTime() { return expectedTime; }
    public String getFlatNumber() { return flatNumber; }
    public String getResidentName() { return residentName; }
    public Long getResidentId() { return residentId; }
    
    // Setters
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public void setVisitorPhone(String visitorPhone) { this.visitorPhone = visitorPhone; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public void setPreApproved(boolean preApproved) { this.preApproved = preApproved; }
    public void setExpectedTime(LocalDateTime expectedTime) { this.expectedTime = expectedTime; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setResidentName(String residentName) { this.residentName = residentName; }

    public void setResidentId(Long residentId) { this.residentId = residentId; }
}