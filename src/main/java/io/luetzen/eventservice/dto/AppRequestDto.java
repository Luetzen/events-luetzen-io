package io.luetzen.eventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating an App.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppRequestDto {
    
    @NotNull(message = "Steam App ID is required")
    @Positive(message = "Steam App ID must be positive")
    private Integer steamAppId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
}