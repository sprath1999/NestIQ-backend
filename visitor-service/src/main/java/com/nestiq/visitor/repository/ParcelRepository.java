package com.nestiq.visitor.repository;

import com.nestiq.visitor.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    List<Parcel> findByResidentIdOrderByArrivedAtDesc(Long residentId);
    List<Parcel> findAllByOrderByArrivedAtDesc();
    List<Parcel> findByCollectedFalseOrderByArrivedAtDesc();
}