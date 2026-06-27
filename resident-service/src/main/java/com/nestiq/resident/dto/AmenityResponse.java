package com.nestiq.resident.dto;

import com.nestiq.resident.entity.AmenityStatus;
import java.time.LocalDateTime;

public class AmenityResponse {

    private Long id;
    private String name;
    private String description;
    private String icon;
    private String availableFrom;
    private String availableTo;
    private int slotDurationMinutes;
    private int maxCapacity;
    private AmenityStatus status;
    private LocalDateTime createdAt;

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getIcon() { return icon; }
    public String getAvailableFrom() { return availableFrom; }
    public String getAvailableTo() { return availableTo; }
    public int getSlotDurationMinutes() { return slotDurationMinutes; }
    public int getMaxCapacity() { return maxCapacity; }
    public AmenityStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setIcon(String icon) { this.icon = icon; }
    public void setAvailableFrom(String availableFrom) { this.availableFrom = availableFrom; }
    public void setAvailableTo(String availableTo) { this.availableTo = availableTo; }
    public void setSlotDurationMinutes(int slotDurationMinutes) { this.slotDurationMinutes = slotDurationMinutes; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public void setStatus(AmenityStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String icon;
        private String availableFrom;
        private String availableTo;
        private int slotDurationMinutes;
        private int maxCapacity;
        private AmenityStatus status;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder icon(String icon) { this.icon = icon; return this; }
        public Builder availableFrom(String availableFrom) { this.availableFrom = availableFrom; return this; }
        public Builder availableTo(String availableTo) { this.availableTo = availableTo; return this; }
        public Builder slotDurationMinutes(int slotDurationMinutes) { this.slotDurationMinutes = slotDurationMinutes; return this; }
        public Builder maxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; return this; }
        public Builder status(AmenityStatus status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public AmenityResponse build() {
            AmenityResponse response = new AmenityResponse();
            response.id = this.id;
            response.name = this.name;
            response.description = this.description;
            response.icon = this.icon;
            response.availableFrom = this.availableFrom;
            response.availableTo = this.availableTo;
            response.slotDurationMinutes = this.slotDurationMinutes;
            response.maxCapacity = this.maxCapacity;
            response.status = this.status;
            response.createdAt = this.createdAt;
            return response;
        }
    }
}