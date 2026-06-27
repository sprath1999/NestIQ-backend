package com.nestiq.resident.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "amenity_bookings")
public class AmenityBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", nullable = false)
    private Amenity amenity;

    @Column(nullable = false)
    private Long residentId;

    @Column(nullable = false)
    private String residentName;

    @Column(nullable = false)
    private String flatNumber;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = BookingStatus.CONFIRMED;
    }

    // Getters
    public Long getId() { return id; }
    public Amenity getAmenity() { return amenity; }
    public Long getResidentId() { return residentId; }
    public String getResidentName() { return residentName; }
    public String getFlatNumber() { return flatNumber; }
    public LocalDate getBookingDate() { return bookingDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setAmenity(Amenity amenity) { this.amenity = amenity; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setStatus(BookingStatus status) { this.status = status; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Amenity amenity;
        private Long residentId;
        private String residentName;
        private String flatNumber;
        private LocalDate bookingDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private BookingStatus status;

        public Builder amenity(Amenity amenity) { this.amenity = amenity; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder bookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; return this; }
        public Builder startTime(LocalTime startTime) { this.startTime = startTime; return this; }
        public Builder endTime(LocalTime endTime) { this.endTime = endTime; return this; }
        public Builder status(BookingStatus status) { this.status = status; return this; }

        public AmenityBooking build() {
            AmenityBooking booking = new AmenityBooking();
            booking.amenity = this.amenity;
            booking.residentId = this.residentId;
            booking.residentName = this.residentName;
            booking.flatNumber = this.flatNumber;
            booking.bookingDate = this.bookingDate;
            booking.startTime = this.startTime;
            booking.endTime = this.endTime;
            booking.status = this.status;
            return booking;
        }
    }
}