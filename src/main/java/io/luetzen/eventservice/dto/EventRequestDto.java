package io.luetzen.eventservice.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.luetzen.eventservice.model.EventType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for creating or updating an Event.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {
    
    @NotNull(message = "Steam App ID is required")
    private Integer appSteamId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Event type is required")
    private EventType eventType;
    
    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;
    
    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;
    
    private String location;
    
    private JsonNode metadata;
}