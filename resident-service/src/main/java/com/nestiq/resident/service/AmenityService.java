package com.nestiq.resident.service;

import com.nestiq.resident.dto.AmenityRequest;
import com.nestiq.resident.dto.AmenityResponse;
import com.nestiq.resident.dto.BookingRequest;
import com.nestiq.resident.dto.BookingResponse;
import com.nestiq.resident.entity.Amenity;
import com.nestiq.resident.entity.AmenityBooking;
import com.nestiq.resident.entity.AmenityStatus;
import com.nestiq.resident.entity.BookingStatus;
import com.nestiq.resident.repository.AmenityBookingRepository;
import com.nestiq.resident.repository.AmenityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityBookingRepository bookingRepository;

    public AmenityService(AmenityRepository amenityRepository,
                          AmenityBookingRepository bookingRepository) {
        this.amenityRepository = amenityRepository;
        this.bookingRepository = bookingRepository;
    }
    
    // Admin creates amenity
    @CacheEvict(value = "amenities", allEntries = true)
    @Transactional
    public AmenityResponse createAmenity(AmenityRequest request) {
        Amenity amenity = Amenity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .icon(request.getIcon())
                .availableFrom(request.getAvailableFrom())
                .availableTo(request.getAvailableTo())
                .slotDurationMinutes(request.getSlotDurationMinutes())
                .maxCapacity(request.getMaxCapacity())
                .status(request.getStatus() != null ? request.getStatus() : AmenityStatus.AVAILABLE)
                .build();

        amenityRepository.save(amenity);
        return mapToAmenityResponse(amenity);
    }

    // Get all amenities
    @Cacheable(value = "amenities", key = "'all'")
    public List<AmenityResponse> getAllAmenities() {
        return amenityRepository.findAll()
                .stream()
                .map(this::mapToAmenityResponse)
                .collect(Collectors.toList());
    }

    // Get available amenities
    @Cacheable(value = "amenities", key = "'available'")
    public List<AmenityResponse> getAvailableAmenities() {
        return amenityRepository.findByStatus(AmenityStatus.AVAILABLE)
                .stream()
                .map(this::mapToAmenityResponse)
                .collect(Collectors.toList());
    }

    // Book amenity — conflict prevention
    @Transactional
    public BookingResponse bookAmenity(BookingRequest request,
                                       Long residentId,
                                       String residentName,
                                       String flatNumber) {
        Amenity amenity = amenityRepository.findById(request.getAmenityId())
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        if (amenity.getStatus() != AmenityStatus.AVAILABLE) {
            throw new RuntimeException("Amenity is not available for booking");
        }

        // Check for conflicting bookings
        List<AmenityBooking> conflicts = bookingRepository.findConflictingBookings(
                request.getAmenityId(),
                request.getBookingDate(),
                request.getStartTime(),
                request.getEndTime()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Slot already booked. Please choose a different time.");
        }

        AmenityBooking booking = AmenityBooking.builder()
                .amenity(amenity)
                .residentId(residentId)
                .residentName(residentName)
                .flatNumber(flatNumber)
                .bookingDate(request.getBookingDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(BookingStatus.CONFIRMED)
                .build();

        bookingRepository.save(booking);
        return mapToBookingResponse(booking);
    }

    // Get my bookings
    public List<BookingResponse> getMyBookings(Long residentId) {
        return bookingRepository.findByResidentIdOrderByCreatedAtDesc(residentId)
                .stream()
                .map(this::mapToBookingResponse)
                .collect(Collectors.toList());
    }

    // Get all bookings (admin)
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToBookingResponse)
                .collect(Collectors.toList());
    }

    // Cancel booking
    @Transactional
    public BookingResponse cancelBooking(Long bookingId, Long residentId) {
        AmenityBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getResidentId().equals(residentId)) {
            throw new RuntimeException("You can only cancel your own bookings");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return mapToBookingResponse(booking);
    }

    private AmenityResponse mapToAmenityResponse(Amenity amenity) {
        return AmenityResponse.builder()
                .id(amenity.getId())
                .name(amenity.getName())
                .description(amenity.getDescription())
                .icon(amenity.getIcon())
                .availableFrom(amenity.getAvailableFrom())
                .availableTo(amenity.getAvailableTo())
                .slotDurationMinutes(amenity.getSlotDurationMinutes())
                .maxCapacity(amenity.getMaxCapacity())
                .status(amenity.getStatus())
                .createdAt(amenity.getCreatedAt())
                .build();
    }

    private BookingResponse mapToBookingResponse(AmenityBooking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .amenityId(booking.getAmenity().getId())
                .amenityName(booking.getAmenity().getName())
                .amenityIcon(booking.getAmenity().getIcon())
                .residentId(booking.getResidentId())
                .residentName(booking.getResidentName())
                .flatNumber(booking.getFlatNumber())
                .bookingDate(booking.getBookingDate())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .build();
    }
}