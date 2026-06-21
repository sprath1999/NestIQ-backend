package com.nestiq.resident.service;

import com.nestiq.resident.dto.ComplaintRequest;
import com.nestiq.resident.dto.ComplaintResponse;
import com.nestiq.resident.dto.UpdateComplaintRequest;
import com.nestiq.resident.entity.Complaint;
import com.nestiq.resident.entity.ComplaintStatus;
import com.nestiq.resident.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Transactional
    public ComplaintResponse createComplaint(ComplaintRequest request,
                                              Long residentId,
                                              String residentName,
                                              String flatNumber) {
        Complaint complaint = Complaint.builder()
                .residentId(residentId)
                .residentName(residentName)
                .flatNumber(flatNumber)
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .status(ComplaintStatus.OPEN)
                .build();

        complaintRepository.save(complaint);
        return mapToResponse(complaint);
    }

    public List<ComplaintResponse> getMyComplaints(Long residentId) {
        return complaintRepository
                .findByResidentIdOrderByCreatedAtDesc(residentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ComplaintResponse getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        return mapToResponse(complaint);
    }

    @Transactional
    public ComplaintResponse updateComplaint(Long id, UpdateComplaintRequest request) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        if (request.getStatus() != null) {
            complaint.setStatus(request.getStatus());
        }
        if (request.getAssignedTo() != null) {
            complaint.setAssignedTo(request.getAssignedTo());
        }
        if (request.getResolutionNote() != null) {
            complaint.setResolutionNote(request.getResolutionNote());
        }

        complaintRepository.save(complaint);
        return mapToResponse(complaint);
    }

    @Transactional
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    public long countByStatus(ComplaintStatus status) {
        return complaintRepository.countByStatus(status);
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {
        return ComplaintResponse.builder()
                .id(complaint.getId())
                .residentId(complaint.getResidentId())
                .residentName(complaint.getResidentName())
                .flatNumber(complaint.getFlatNumber())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .status(complaint.getStatus())
                .assignedTo(complaint.getAssignedTo())
                .resolutionNote(complaint.getResolutionNote())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .build();
    }
}