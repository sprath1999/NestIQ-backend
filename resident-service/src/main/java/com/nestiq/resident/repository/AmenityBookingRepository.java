package com.nestiq.resident.repository;

import com.nestiq.resident.entity.AmenityBooking;
import com.nestiq.resident.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AmenityBookingRepository extends JpaRepository<AmenityBooking, Long> {

    List<AmenityBooking> findByResidentIdOrderByCreatedAtDesc(Long residentId);

    List<AmenityBooking> findByAmenityIdAndBookingDateAndStatus(
            Long amenityId, LocalDate bookingDate, BookingStatus status);

    @Query("SELECT b FROM AmenityBooking b WHERE b.amenity.id = :amenityId " +
           "AND b.bookingDate = :date AND b.status = 'CONFIRMED' " +
           "AND ((b.startTime < :endTime AND b.endTime > :startTime))")
    List<AmenityBooking> findConflictingBookings(
            @Param("amenityId") Long amenityId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    List<AmenityBooking> findAllByOrderByCreatedAtDesc();
}