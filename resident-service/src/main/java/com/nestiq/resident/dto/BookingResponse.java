package com.nestiq.resident.dto;

import com.nestiq.resident.entity.BookingStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingResponse {

    private Long id;
    private Long amenityId;
    private String amenityName;
    private String amenityIcon;
    private Long residentId;
    private String residentName;
    private String flatNumber;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BookingStatus status;
    private LocalDateTime createdAt;

    // Getters
    public Long getId() { return id; }
    public Long getAmenityId() { return amenityId; }
    public String getAmenityName() { return amenityName; }
    public String getAmenityIcon() { return amenityIcon; }
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
    public void setAmenityId(Long amenityId) { this.amenityId = amenityId; }
    public void setAmenityName(String amenityName) { this.amenityName = amenityName; }
    public void setAmenityIcon(String amenityIcon) { this.amenityIcon = amenityIcon; }
    public void setResidentId(Long residentId) { this.residentId = residentId; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long amenityId;
        private String amenityName;
        private String amenityIcon;
        private Long residentId;
        private String residentName;
        private String flatNumber;
        private LocalDate bookingDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private BookingStatus status;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder amenityId(Long amenityId) { this.amenityId = amenityId; return this; }
        public Builder amenityName(String amenityName) { this.amenityName = amenityName; return this; }
        public Builder amenityIcon(String amenityIcon) { this.amenityIcon = amenityIcon; return this; }
        public Builder residentId(Long residentId) { this.residentId = residentId; return this; }
        public Builder residentName(String residentName) { this.residentName = residentName; return this; }
        public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
        public Builder bookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; return this; }
        public Builder startTime(LocalTime startTime) { this.startTime = startTime; return this; }
        public Builder endTime(LocalTime endTime) { this.endTime = endTime; return this; }
        public Builder status(BookingStatus status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public BookingResponse build() {
            BookingResponse response = new BookingResponse();
            response.id = this.id;
            response.amenityId = this.amenityId;
            response.amenityName = this.amenityName;
            response.amenityIcon = this.amenityIcon;
            response.residentId = this.residentId;
            response.residentName = this.residentName;
            response.flatNumber = this.flatNumber;
            response.bookingDate = this.bookingDate;
            response.startTime = this.startTime;
            response.endTime = this.endTime;
            response.status = this.status;
            response.createdAt = this.createdAt;
            return response;
        }
    }
}