package com.nestiq.resident.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String availableFrom;

    @Column(nullable = false)
    private String availableTo;

    @Column(nullable = false)
    private int slotDurationMinutes;

    @Column(nullable = false)
    private int maxCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmenityStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = AmenityStatus.AVAILABLE;
    }

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

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String name;
        private String description;
        private String icon;
        private String availableFrom;
        private String availableTo;
        private int slotDurationMinutes;
        private int maxCapacity;
        private AmenityStatus status;

        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder icon(String icon) { this.icon = icon; return this; }
        public Builder availableFrom(String availableFrom) { this.availableFrom = availableFrom; return this; }
        public Builder availableTo(String availableTo) { this.availableTo = availableTo; return this; }
        public Builder slotDurationMinutes(int slotDurationMinutes) { this.slotDurationMinutes = slotDurationMinutes; return this; }
        public Builder maxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; return this; }
        public Builder status(AmenityStatus status) { this.status = status; return this; }

        public Amenity build() {
            Amenity amenity = new Amenity();
            amenity.name = this.name;
            amenity.description = this.description;
            amenity.icon = this.icon;
            amenity.availableFrom = this.availableFrom;
            amenity.availableTo = this.availableTo;
            amenity.slotDurationMinutes = this.slotDurationMinutes;
            amenity.maxCapacity = this.maxCapacity;
            amenity.status = this.status;
            return amenity;
        }
    }
}