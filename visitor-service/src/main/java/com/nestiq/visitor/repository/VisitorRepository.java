package com.nestiq.visitor.repository;

import com.nestiq.visitor.entity.Visitor;
import com.nestiq.visitor.entity.VisitorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByResidentIdOrderByCreatedAtDesc(Long residentId);
    List<Visitor> findAllByOrderByCreatedAtDesc();
    List<Visitor> findByStatusOrderByCreatedAtDesc(VisitorStatus status);
    List<Visitor> findByPreApprovedTrueAndStatusOrderByExpectedTimeAsc(VisitorStatus status);
}