package com.nestiq.resident.dto;

import jakarta.validation.constraints.NotBlank;

public class FlatRequest {

    @NotBlank(message = "Flat number is required")
    private String flatNumber;

    @NotBlank(message = "Block is required")
    private String block;

    @NotBlank(message = "Type is required")
    private String type;

    private Long residentId;
    private String residentName;
    private String residentEmail;
    private String residentPhone;

    // Getters
    public String getFlatNumber() { return flatNumber; }
    public String getBlock() { return block; }
    public String getType() { return type; }
    public Long getResidentId() { return residentId; }
    public String getResidentName() { return residentName; }
    public String getResidentEmail() { return residentEmail; }
    public String getResidentPhone() { return residentPhone; }

    // Setters
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setBlock(String block) { this.block = block; }
    public void setType(String type) { this.type = type; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setResidentEmail(String residentEmail) { this.residentEmail = residentEmail; }
    public void setResidentPhone(String residentPhone) { this.residentPhone = residentPhone; }
}