package com.nestiq.resident.service;

import com.nestiq.resident.dto.NoticeRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import com.nestiq.resident.dto.NoticeResponse;
import com.nestiq.resident.entity.Notice;
import com.nestiq.resident.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    
    @CacheEvict(value = "notices", allEntries = true)
    @Transactional
    public NoticeResponse createNotice(NoticeRequest request) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .pinned(request.isPinned())
                .build();
        noticeRepository.save(notice);
        return mapToResponse(notice);
    }

    @Cacheable(value = "notices", key = "'all'")
    public List<NoticeResponse> getAllNotices() {
        return noticeRepository.findAllByOrderByPinnedDescCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "notices", allEntries = true)
    @Transactional
    public NoticeResponse updateNotice(Long id, NoticeRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
        notice.setTitle(request.getTitle());
        notice.setContent(request.getContent());
        notice.setCategory(request.getCategory());
        notice.setPinned(request.isPinned());
        noticeRepository.save(notice);
        return mapToResponse(notice);
    }

    @CacheEvict(value = "notices", allEntries = true)
    @Transactional
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    private NoticeResponse mapToResponse(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .category(notice.getCategory())
                .pinned(notice.isPinned())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}