package com.nestiq.resident.dto;

import com.nestiq.resident.entity.ComplaintCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComplaintRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private ComplaintCategory category;

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ComplaintCategory getCategory() { return category; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(ComplaintCategory category) { this.category = category; }
}