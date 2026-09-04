package com.nestiq.resident.repository;

import com.nestiq.resident.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    Optional<Flat> findByFlatNumber(String flatNumber);
    List<Flat> findAllByOrderByFlatNumberAsc();
    List<Flat> findByBlock(String block);
}