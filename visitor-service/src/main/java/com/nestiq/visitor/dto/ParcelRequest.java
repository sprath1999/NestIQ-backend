package com.nestiq.visitor.dto;

import jakarta.validation.constraints.NotBlank;

public class ParcelRequest {

    @NotBlank(message = "Flat number is required")
    private String flatNumber;

    @NotBlank(message = "Resident name is required")
    private String residentName;

    @NotBlank(message = "Sender is required")
    private String sender;

    private String description;
    private Long residentId;

    // Getters
    public String getFlatNumber() { return flatNumber; }
    public String getResidentName() { return residentName; }
    public String getSender() { return sender; }
    public String getDescription() { return description; }
    public Long getResidentId() { return residentId; }

    // Setters
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setSender(String sender) { this.sender = sender; }
    public void setDescription(String description) { this.description = description; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
}