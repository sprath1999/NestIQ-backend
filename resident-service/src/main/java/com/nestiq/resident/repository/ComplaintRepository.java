package com.nestiq.resident.repository;

import com.nestiq.resident.entity.Complaint;
import com.nestiq.resident.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByResidentIdOrderByCreatedAtDesc(Long residentId);
    List<Complaint> findAllByOrderByCreatedAtDesc();
    List<Complaint> findByStatusOrderByCreatedAtDesc(ComplaintStatus status);
    long countByStatus(ComplaintStatus status);
}