package com.nestiq.resident.dto;

import com.nestiq.resident.entity.ComplaintStatus;

public class UpdateComplaintRequest {

    private ComplaintStatus status;
    private String assignedTo;
    private String resolutionNote;

    // Getters
    public ComplaintStatus getStatus() { return status; }
    public String getAssignedTo() { return assignedTo; }
    public String getResolutionNote() { return resolutionNote; }

    // Setters
    public void setStatus(ComplaintStatus status) { this.status = status; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setResolutionNote(String resolutionNote) { this.resolutionNote = resolutionNote; }
}