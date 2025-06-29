package io.luetzen.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for returning Team data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    
    private UUID id;
    private String name;
    private Integer members;
    private LocalDateTime createdAt;
}