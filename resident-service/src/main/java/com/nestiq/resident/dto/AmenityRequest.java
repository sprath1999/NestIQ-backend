package com.nestiq.resident.dto;

import com.nestiq.resident.entity.AmenityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AmenityRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Icon is required")
    private String icon;

    @NotBlank(message = "Available from is required")
    private String availableFrom;

    @NotBlank(message = "Available to is required")
    private String availableTo;

    @NotNull(message = "Slot duration is required")
    private int slotDurationMinutes;

    @NotNull(message = "Max capacity is required")
    private int maxCapacity;

    private AmenityStatus status;

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getIcon() { return icon; }
    public String getAvailableFrom() { return availableFrom; }
    public String getAvailableTo() { return availableTo; }
    public int getSlotDurationMinutes() { return slotDurationMinutes; }
    public int getMaxCapacity() { return maxCapacity; }
    public AmenityStatus getStatus() { return status; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setIcon(String icon) { this.icon = icon; }
    public void setAvailableFrom(String availableFrom) { this.availableFrom = availableFrom; }
    public void setAvailableTo(String availableTo) { this.availableTo = availableTo; }
    public void setSlotDurationMinutes(int slotDurationMinutes) { this.slotDurationMinutes = slotDurationMinutes; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public void setStatus(AmenityStatus status) { this.status = status; }
}