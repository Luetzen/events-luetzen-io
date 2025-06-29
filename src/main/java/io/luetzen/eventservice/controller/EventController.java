package io.luetzen.eventservice.controller;

import io.luetzen.eventservice.dto.EventRequestDto;
import io.luetzen.eventservice.dto.EventResponseDto;
import io.luetzen.eventservice.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for Event operations.
 */
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event", description = "Event management APIs")
public class EventController {
    
    private final EventService eventService;
    
    /**
     * Create a new Event.
     *
     * @param eventRequestDto the Event data
     * @return the created Event
     */
    @PostMapping
    @Operation(summary = "Create a new Event", description = "Creates a new Event with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(schema = @Schema(implementation = EventResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Associated App not found")
    })
    public ResponseEntity<EventResponseDto> createEvent(
            @Valid @RequestBody EventRequestDto eventRequestDto) {
        
        EventResponseDto createdEvent = eventService.createEvent(eventRequestDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
    
    /**
     * Get an Event by its ID.
     *
     * @param id the Event ID
     * @return the Event
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an Event by ID", description = "Returns an Event by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found",
                    content = @Content(schema = @Schema(implementation = EventResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<EventResponseDto> getEventById(
            @Parameter(description = "Event ID", required = true)
            @PathVariable UUID id) {
        
        EventResponseDto event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
    
    /**
     * Get Events by App's Steam App ID.
     *
     * @param appId the Steam App ID
     * @param pageable pagination information
     * @return a page of Events
     */
    @GetMapping
    @Operation(summary = "Get Events by App's Steam App ID", 
            description = "Returns a page of Events associated with the specified App")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found"),
            @ApiResponse(responseCode = "404", description = "Associated App not found")
    })
    public ResponseEntity<Page<EventResponseDto>> getEventsByAppSteamAppId(
            @Parameter(description = "Steam App ID", required = true)
            @RequestParam Integer appId,
            @PageableDefault(size = 20) Pageable pageable) {
        
        Page<EventResponseDto> events = eventService.getEventsByAppSteamAppId(appId, pageable);
        return ResponseEntity.ok(events);
    }
}