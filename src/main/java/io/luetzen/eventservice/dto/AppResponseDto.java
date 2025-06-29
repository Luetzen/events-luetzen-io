package io.luetzen.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for returning App data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppResponseDto {
    
    private UUID id;
    private Integer steamAppId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}