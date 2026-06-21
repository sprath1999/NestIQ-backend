package com.nestiq.resident.controller;

import com.nestiq.resident.dto.ComplaintRequest;
import com.nestiq.resident.dto.ComplaintResponse;
import com.nestiq.resident.dto.UpdateComplaintRequest;
import com.nestiq.resident.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Resident raises a complaint
    @PostMapping
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody ComplaintRequest request,
            Authentication authentication) {

        Long residentId = (Long) authentication.getCredentials();
        String residentName = authentication.getName();

        // flatNumber will come from request header (set by frontend)
        String flatNumber = "N/A";

        return ResponseEntity.ok(
                complaintService.createComplaint(request, residentId, residentName, flatNumber)
        );
    }

    // Resident gets their own complaints
    @GetMapping("/my")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<List<ComplaintResponse>> getMyComplaints(Authentication authentication) {
        Long residentId = (Long) authentication.getCredentials();
        return ResponseEntity.ok(complaintService.getMyComplaints(residentId));
    }

    // Admin gets all complaints
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    // Get single complaint
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getComplaintById(id));
    }

    // Admin updates complaint status
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComplaintResponse> updateComplaint(
            @PathVariable Long id,
            @RequestBody UpdateComplaintRequest request) {
        return ResponseEntity.ok(complaintService.updateComplaint(id, request));
    }

    // Admin deletes complaint
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully");
    }
}