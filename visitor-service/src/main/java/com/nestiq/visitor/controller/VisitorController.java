package com.nestiq.visitor.controller;

import com.nestiq.visitor.dto.ParcelRequest;
import com.nestiq.visitor.dto.ParcelResponse;
import com.nestiq.visitor.dto.VisitorRequest;
import com.nestiq.visitor.dto.VisitorResponse;
import com.nestiq.visitor.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    // Resident pre-approves visitor
    @PostMapping("/pre-approve")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<VisitorResponse> preApproveVisitor(
            @Valid @RequestBody VisitorRequest request,
            Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(visitorService.preApproveVisitor(request, residentId));
    }

    // Guard logs entry
    @PostMapping("/entry")
    @PreAuthorize("hasRole('GUARD')")
    public ResponseEntity<VisitorResponse> logEntry(
            @Valid @RequestBody VisitorRequest request) {
        return ResponseEntity.ok(visitorService.logEntry(request));
    }

    // Guard logs exit
    @PutMapping("/{id}/exit")
    @PreAuthorize("hasRole('GUARD')")
    public ResponseEntity<VisitorResponse> logExit(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.logExit(id));
    }

    // Resident gets their visitors
    @GetMapping("/my")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<List<VisitorResponse>> getMyVisitors(Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(visitorService.getMyVisitors(residentId));
    }

    // Admin/Guard gets all visitors
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('GUARD')")
    public ResponseEntity<List<VisitorResponse>> getAllVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    // Get visitors currently inside
    @GetMapping("/inside")
    @PreAuthorize("hasRole('ADMIN') or hasRole('GUARD')")
    public ResponseEntity<List<VisitorResponse>> getVisitorsInside() {
        return ResponseEntity.ok(visitorService.getVisitorsInside());
    }

    // Get pre-approved visitors
    @GetMapping("/pre-approved")
    @PreAuthorize("hasRole('GUARD')")
    public ResponseEntity<List<VisitorResponse>> getPreApprovedVisitors() {
        return ResponseEntity.ok(visitorService.getPreApprovedVisitors());
    }

    // Guard logs parcel
    @PostMapping("/parcels")
    @PreAuthorize("hasRole('GUARD')")
    public ResponseEntity<ParcelResponse> logParcel(
            @Valid @RequestBody ParcelRequest request) {
        return ResponseEntity.ok(visitorService.logParcel(request));
    }

    // Resident gets their parcels
    @GetMapping("/parcels/my")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<List<ParcelResponse>> getMyParcels(Authentication authentication) {
        Long residentId = ((Number) authentication.getCredentials()).longValue();
        return ResponseEntity.ok(visitorService.getMyParcels(residentId));
    }

    // Guard gets all parcels
    @GetMapping("/parcels")
    @PreAuthorize("hasRole('GUARD') or hasRole('ADMIN')")
    public ResponseEntity<List<ParcelResponse>> getAllParcels() {
        return ResponseEntity.ok(visitorService.getAllParcels());
    }

    // Get uncollected parcels
    @GetMapping("/parcels/uncollected")
    @PreAuthorize("hasRole('GUARD')")
    public ResponseEntity<List<ParcelResponse>> getUncollectedParcels() {
        return ResponseEntity.ok(visitorService.getUncollectedParcels());
    }

    // Mark parcel collected
    @PutMapping("/parcels/{id}/collect")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<ParcelResponse> markParcelCollected(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.markParcelCollected(id));
    }
}