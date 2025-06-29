package io.luetzen.eventservice.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.luetzen.eventservice.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for returning Event data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    
    private UUID id;
    private AppResponseDto app;
    private String name;
    private String description;
    private EventType eventType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private JsonNode metadata;
    private LocalDateTime createdAt;
}