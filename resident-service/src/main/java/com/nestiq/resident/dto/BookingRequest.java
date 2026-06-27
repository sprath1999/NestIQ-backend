package com.nestiq.resident.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {

    @NotNull(message = "Amenity ID is required")
    private Long amenityId;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    // Getters
    public Long getAmenityId() { return amenityId; }
    public LocalDate getBookingDate() { return bookingDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    // Setters
    public void setAmenityId(Long amenityId) { this.amenityId = amenityId; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}