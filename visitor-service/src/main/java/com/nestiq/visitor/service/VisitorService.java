package com.nestiq.visitor.service;

import com.nestiq.visitor.dto.ParcelRequest;
import com.nestiq.visitor.dto.ParcelResponse;
import com.nestiq.visitor.dto.VisitorRequest;
import com.nestiq.visitor.dto.VisitorResponse;
import com.nestiq.visitor.entity.Parcel;
import com.nestiq.visitor.entity.Visitor;
import com.nestiq.visitor.entity.VisitorStatus;
import com.nestiq.visitor.repository.ParcelRepository;
import com.nestiq.visitor.repository.VisitorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final ParcelRepository parcelRepository;

    public VisitorService(VisitorRepository visitorRepository,
                          ParcelRepository parcelRepository) {
        this.visitorRepository = visitorRepository;
        this.parcelRepository = parcelRepository;
    }

    // Resident pre-approves visitor
    @Transactional
    public VisitorResponse preApproveVisitor(VisitorRequest request, Long residentId) {
        Visitor visitor = Visitor.builder()
                .visitorName(request.getVisitorName())
                .visitorPhone(request.getVisitorPhone())
                .purpose(request.getPurpose())
                .residentId(residentId)
                .flatNumber(request.getFlatNumber())
                .residentName(request.getResidentName())
                .preApproved(true)
                .expectedTime(request.getExpectedTime())
                .status(VisitorStatus.EXPECTED)
                .build();
        visitorRepository.save(visitor);
        return mapToVisitorResponse(visitor);
    }

    // Guard logs visitor entry
    @Transactional
    public VisitorResponse logEntry(VisitorRequest request) {
        Visitor visitor = Visitor.builder()
                .visitorName(request.getVisitorName())
                .visitorPhone(request.getVisitorPhone())
                .purpose(request.getPurpose())
                .residentId(request.getResidentId() != null ? request.getResidentId() : 0L)
                .flatNumber(request.getFlatNumber())
                .residentName(request.getResidentName())
                .preApproved(false)
                .status(VisitorStatus.INSIDE)
                .build();
        visitor.setEntryTime(LocalDateTime.now());
        visitorRepository.save(visitor);
        return mapToVisitorResponse(visitor);
    }

    // Guard logs visitor exit
    @Transactional
    public VisitorResponse logExit(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        visitor.setStatus(VisitorStatus.EXITED);
        visitor.setExitTime(LocalDateTime.now());
        visitorRepository.save(visitor);
        return mapToVisitorResponse(visitor);
    }

    // Get my visitors (resident)
    public List<VisitorResponse> getMyVisitors(Long residentId) {
        return visitorRepository.findByResidentIdOrderByCreatedAtDesc(residentId)
                .stream()
                .map(this::mapToVisitorResponse)
                .collect(Collectors.toList());
    }

    // Get all visitors (admin/guard)
    public List<VisitorResponse> getAllVisitors() {
        return visitorRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToVisitorResponse)
                .collect(Collectors.toList());
    }

    // Get visitors currently inside
    public List<VisitorResponse> getVisitorsInside() {
        return visitorRepository.findByStatusOrderByCreatedAtDesc(VisitorStatus.INSIDE)
                .stream()
                .map(this::mapToVisitorResponse)
                .collect(Collectors.toList());
    }

    // Get pre-approved visitors
    public List<VisitorResponse> getPreApprovedVisitors() {
        return visitorRepository
                .findByPreApprovedTrueAndStatusOrderByExpectedTimeAsc(VisitorStatus.EXPECTED)
                .stream()
                .map(this::mapToVisitorResponse)
                .collect(Collectors.toList());
    }

    // Guard logs parcel
    @Transactional
    public ParcelResponse logParcel(ParcelRequest request) {
        Parcel parcel = Parcel.builder()
                .residentId(request.getResidentId() != null ? request.getResidentId() : 0L)
                .flatNumber(request.getFlatNumber())
                .residentName(request.getResidentName())
                .sender(request.getSender())
                .description(request.getDescription())
                .build();
        parcelRepository.save(parcel);
        return mapToParcelResponse(parcel);
    }

    // Get my parcels (resident)
    public List<ParcelResponse> getMyParcels(Long residentId) {
        return parcelRepository.findByResidentIdOrderByArrivedAtDesc(residentId)
                .stream()
                .map(this::mapToParcelResponse)
                .collect(Collectors.toList());
    }

    // Get all parcels (guard/admin)
    public List<ParcelResponse> getAllParcels() {
        return parcelRepository.findAllByOrderByArrivedAtDesc()
                .stream()
                .map(this::mapToParcelResponse)
                .collect(Collectors.toList());
    }

    // Get uncollected parcels
    public List<ParcelResponse> getUncollectedParcels() {
        return parcelRepository.findByCollectedFalseOrderByArrivedAtDesc()
                .stream()
                .map(this::mapToParcelResponse)
                .collect(Collectors.toList());
    }

    // Mark parcel as collected
    @Transactional
    public ParcelResponse markParcelCollected(Long parcelId) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
        parcel.setCollected(true);
        parcel.setCollectedAt(LocalDateTime.now());
        parcelRepository.save(parcel);
        return mapToParcelResponse(parcel);
    }

    private VisitorResponse mapToVisitorResponse(Visitor visitor) {
        return VisitorResponse.builder()
                .id(visitor.getId())
                .visitorName(visitor.getVisitorName())
                .visitorPhone(visitor.getVisitorPhone())
                .purpose(visitor.getPurpose())
                .residentId(visitor.getResidentId())
                .flatNumber(visitor.getFlatNumber())
                .residentName(visitor.getResidentName())
                .status(visitor.getStatus())
                .preApproved(visitor.isPreApproved())
                .entryTime(visitor.getEntryTime())
                .exitTime(visitor.getExitTime())
                .expectedTime(visitor.getExpectedTime())
                .createdAt(visitor.getCreatedAt())
                .build();
    }

    private ParcelResponse mapToParcelResponse(Parcel parcel) {
        return ParcelResponse.builder()
                .id(parcel.getId())
                .residentId(parcel.getResidentId())
                .flatNumber(parcel.getFlatNumber())
                .residentName(parcel.getResidentName())
                .sender(parcel.getSender())
                .description(parcel.getDescription())
                .collected(parcel.isCollected())
                .collectedAt(parcel.getCollectedAt())
                .arrivedAt(parcel.getArrivedAt())
                .build();
    }
}