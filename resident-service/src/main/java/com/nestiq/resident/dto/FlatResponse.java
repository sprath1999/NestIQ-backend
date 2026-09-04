package com.nestiq.resident.dto;

import java.time.LocalDateTime;

public class FlatResponse {

    private Long id;
    private String flatNumber;
    private String block;
    private String type;
    private Long residentId;
    private String residentName;
    private String residentEmail;
    private String residentPhone;
    private LocalDateTime createdAt;

    // Getters
    public Long getId() { return id; }
    public String getFlatNumber() { return flatNumber; }
    public String getBlock() { return block; }
    public String getType() { return type; }
    public Long getResidentId() { return residentId; }
    public String getResidentName() { return residentName; }
    public String getResidentEmail() { return residentEmail; }
    public String getResidentPhone() { return residentPhone; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setBlock(String block) { this.block = block; }
    public void setType(String type) { this.type = type; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setResidentEmail(String residentEmail) { this.residentEmail = residentEmail; }
    public void setResidentPhone(String residentPhone) { this.residentPhone = residentPhone; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String flatNumber;
        private String block;
        private String type;
        private Long residentId;
        private String residentName;
        private String residentEmail;
        private String residentPhone;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder block(String block) { this.block = block; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder residentEmail(String residentEmail) { this.residentEmail = residentEmail; return this; }
        public Builder residentPhone(String residentPhone) { this.residentPhone = residentPhone; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public FlatResponse build() {
            FlatResponse response = new FlatResponse();
            response.id = this.id;
            response.flatNumber = this.flatNumber;
            response.block = this.block;
            response.type = this.type;
            response.residentId = this.residentId;
            response.residentName = this.residentName;
            response.residentEmail = this.residentEmail;
            response.residentPhone = this.residentPhone;
            response.createdAt = this.createdAt;
            return response;
        }
    }
}