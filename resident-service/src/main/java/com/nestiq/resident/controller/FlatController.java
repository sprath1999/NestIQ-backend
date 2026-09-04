package com.nestiq.resident.controller;

import com.nestiq.resident.dto.FlatRequest;
import com.nestiq.resident.dto.FlatResponse;
import com.nestiq.resident.service.FlatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flats")
public class FlatController {

    private final FlatService flatService;

    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    // Admin creates flat
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlatResponse> createFlat(
            @Valid @RequestBody FlatRequest request) {
        return ResponseEntity.ok(flatService.createFlat(request));
    }

    // Get all flats — admin
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FlatResponse>> getAllFlats() {
        return ResponseEntity.ok(flatService.getAllFlats());
    }

    // Lookup flat by number — guard uses this
    @GetMapping("/lookup/{flatNumber}")
    public ResponseEntity<FlatResponse> getFlatByNumber(
            @PathVariable String flatNumber) {
        return ResponseEntity.ok(flatService.getFlatByNumber(flatNumber));
    }

    // Update flat — admin
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlatResponse> updateFlat(
            @PathVariable Long id,
            @Valid @RequestBody FlatRequest request) {
        return ResponseEntity.ok(flatService.updateFlat(id, request));
    }

    // Delete flat — admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFlat(@PathVariable Long id) {
        flatService.deleteFlat(id);
        return ResponseEntity.ok("Flat deleted successfully");
    }
}