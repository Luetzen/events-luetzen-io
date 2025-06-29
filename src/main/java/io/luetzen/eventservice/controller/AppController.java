package io.luetzen.eventservice.controller;

import io.luetzen.eventservice.dto.AppRequestDto;
import io.luetzen.eventservice.dto.AppResponseDto;
import io.luetzen.eventservice.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for App operations.
 */
@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
@Tag(name = "App", description = "App management APIs")
public class AppController {
    
    private final AppService appService;
    
    /**
     * Create a new App.
     *
     * @param appRequestDto the App data
     * @return the created App
     */
    @PostMapping
    @Operation(summary = "Create a new App", description = "Creates a new App with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "App created successfully",
                    content = @Content(schema = @Schema(implementation = AppResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "App with the same Steam App ID already exists")
    })
    public ResponseEntity<AppResponseDto> createApp(
            @Valid @RequestBody AppRequestDto appRequestDto) {
        
        AppResponseDto createdApp = appService.createApp(appRequestDto);
        return new ResponseEntity<>(createdApp, HttpStatus.CREATED);
    }
    
    /**
     * Get an App by its ID.
     *
     * @param id the App ID
     * @return the App
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an App by ID", description = "Returns an App by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "App found",
                    content = @Content(schema = @Schema(implementation = AppResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    public ResponseEntity<AppResponseDto> getAppById(
            @Parameter(description = "App ID", required = true)
            @PathVariable UUID id) {
        
        AppResponseDto app = appService.getAppById(id);
        return ResponseEntity.ok(app);
    }
    
    /**
     * Get an App by its Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @return the App
     */
    @GetMapping("/steam/{steamAppId}")
    @Operation(summary = "Get an App by Steam App ID", description = "Returns an App by its Steam App ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "App found",
                    content = @Content(schema = @Schema(implementation = AppResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    public ResponseEntity<AppResponseDto> getAppBySteamAppId(
            @Parameter(description = "Steam App ID", required = true)
            @PathVariable Integer steamAppId) {
        
        AppResponseDto app = appService.getAppBySteamAppId(steamAppId);
        return ResponseEntity.ok(app);
    }
}