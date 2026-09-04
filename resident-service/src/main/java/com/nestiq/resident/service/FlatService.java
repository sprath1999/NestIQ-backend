package com.nestiq.resident.service;

import com.nestiq.resident.dto.FlatRequest;
import com.nestiq.resident.dto.FlatResponse;
import com.nestiq.resident.entity.Flat;
import com.nestiq.resident.repository.FlatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatService {

    private final FlatRepository flatRepository;

    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Transactional
    public FlatResponse createFlat(FlatRequest request) {
        if (flatRepository.findByFlatNumber(request.getFlatNumber()).isPresent()) {
            throw new RuntimeException("Flat already exists");
        }

        Flat flat = Flat.builder()
                .flatNumber(request.getFlatNumber())
                .block(request.getBlock())
                .type(request.getType())
                .residentId(request.getResidentId())
                .residentName(request.getResidentName())
                .residentEmail(request.getResidentEmail())
                .residentPhone(request.getResidentPhone())
                .build();

        flatRepository.save(flat);
        return mapToResponse(flat);
    }

    public List<FlatResponse> getAllFlats() {
        return flatRepository.findAllByOrderByFlatNumberAsc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public FlatResponse getFlatByNumber(String flatNumber) {
        Flat flat = flatRepository.findByFlatNumber(flatNumber)
                .orElseThrow(() -> new RuntimeException("Flat not found: " + flatNumber));
        return mapToResponse(flat);
    }

    @Transactional
    public FlatResponse updateFlat(Long id, FlatRequest request) {
        Flat flat = flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        flat.setFlatNumber(request.getFlatNumber());
        flat.setBlock(request.getBlock());
        flat.setType(request.getType());
        flat.setResidentId(request.getResidentId());
        flat.setResidentName(request.getResidentName());
        flat.setResidentEmail(request.getResidentEmail());
        flat.setResidentPhone(request.getResidentPhone());

        flatRepository.save(flat);
        return mapToResponse(flat);
    }

    @Transactional
    public void deleteFlat(Long id) {
        flatRepository.deleteById(id);
    }

    private FlatResponse mapToResponse(Flat flat) {
        return FlatResponse.builder()
                .id(flat.getId())
                .flatNumber(flat.getFlatNumber())
                .block(flat.getBlock())
                .type(flat.getType())
                .residentId(flat.getResidentId())
                .residentName(flat.getResidentName())
                .residentEmail(flat.getResidentEmail())
                .residentPhone(flat.getResidentPhone())
                .createdAt(flat.getCreatedAt())
                .build();
    }
}