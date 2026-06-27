package com.nestiq.resident.controller;

import com.nestiq.resident.dto.AmenityRequest;
import com.nestiq.resident.dto.AmenityResponse;
import com.nestiq.resident.dto.BookingRequest;
import com.nestiq.resident.dto.BookingResponse;
import com.nestiq.resident.service.AmenityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    // Admin creates amenity
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AmenityResponse> createAmenity(
            @Valid @RequestBody AmenityRequest request) {
        return ResponseEntity.ok(amenityService.createAmenity(request));
    }

    // Get all amenities
    @GetMapping
    public ResponseEntity<List<AmenityResponse>> getAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    // Get available amenities
    @GetMapping("/available")
    public ResponseEntity<List<AmenityResponse>> getAvailableAmenities() {
        return ResponseEntity.ok(amenityService.getAvailableAmenities());
    }

    // Resident books amenity
    @PostMapping("/book")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<BookingResponse> bookAmenity(
            @Valid @RequestBody BookingRequest request,
            Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        String residentName = authentication.getName();
        return ResponseEntity.ok(
                amenityService.bookAmenity(request, residentId, residentName, "N/A")
        );
    }

    // Get my bookings
    @GetMapping("/my-bookings")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<List<BookingResponse>> getMyBookings(Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(amenityService.getMyBookings(residentId));
    }

    // Admin gets all bookings
    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(amenityService.getAllBookings());
    }

    // Cancel booking
    @PutMapping("/bookings/{id}/cancel")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long id,
            Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(amenityService.cancelBooking(id, residentId));
    }
}