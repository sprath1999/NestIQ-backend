package com.nestiq.resident.repository;

import com.nestiq.resident.entity.Amenity;
import com.nestiq.resident.entity.AmenityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    List<Amenity> findByStatus(AmenityStatus status);
}