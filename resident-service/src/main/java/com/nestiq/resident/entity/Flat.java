package com.nestiq.resident.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flatNumber;

    @Column(nullable = false)
    private String block;

    @Column(nullable = false)
    private String type;

    private Long residentId;
    private String residentName;
    private String residentEmail;
    private String residentPhone;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

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

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String flatNumber;
        private String block;
        private String type;
        private Long residentId;
        private String residentName;
        private String residentEmail;
        private String residentPhone;

        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder block(String block) { this.block = block; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder residentEmail(String residentEmail) { this.residentEmail = residentEmail; return this; }
        public Builder residentPhone(String residentPhone) { this.residentPhone = residentPhone; return this; }

        public Flat build() {
            Flat flat = new Flat();
            flat.flatNumber = this.flatNumber;
            flat.block = this.block;
            flat.type = this.type;
            flat.residentId = this.residentId;
            flat.residentName = this.residentName;
            flat.residentEmail = this.residentEmail;
            flat.residentPhone = this.residentPhone;
            return flat;
        }
    }
}