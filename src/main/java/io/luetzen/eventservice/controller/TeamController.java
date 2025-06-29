package io.luetzen.eventservice.controller;

import io.luetzen.eventservice.dto.TeamRequestDto;
import io.luetzen.eventservice.dto.TeamResponseDto;
import io.luetzen.eventservice.service.TeamService;
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

import java.util.List;
import java.util.UUID;

/**
 * REST controller for Team operations.
 */
@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@Tag(name = "Team", description = "Team management APIs")
public class TeamController {
    
    private final TeamService teamService;
    
    /**
     * Create a new Team.
     *
     * @param teamRequestDto the Team data
     * @return the created Team
     */
    @PostMapping
    @Operation(summary = "Create a new Team", description = "Creates a new Team with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created successfully",
                    content = @Content(schema = @Schema(implementation = TeamResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TeamResponseDto> createTeam(
            @Valid @RequestBody TeamRequestDto teamRequestDto) {
        
        TeamResponseDto createdTeam = teamService.createTeam(teamRequestDto);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }
    
    /**
     * Get a Team by its ID.
     *
     * @param id the Team ID
     * @return the Team
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a Team by ID", description = "Returns a Team by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team found",
                    content = @Content(schema = @Schema(implementation = TeamResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    public ResponseEntity<TeamResponseDto> getTeamById(
            @Parameter(description = "Team ID", required = true)
            @PathVariable UUID id) {
        
        TeamResponseDto team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }
    
    /**
     * Find Teams by name (partial match, case-insensitive).
     *
     * @param name the name to search for
     * @return a list of Teams
     */
    @GetMapping("/search")
    @Operation(summary = "Find Teams by name", 
            description = "Returns Teams with names containing the search term (case-insensitive)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found")
    })
    public ResponseEntity<List<TeamResponseDto>> findTeamsByName(
            @Parameter(description = "Name to search for", required = true)
            @RequestParam String name) {
        
        List<TeamResponseDto> teams = teamService.findTeamsByName(name);
        return ResponseEntity.ok(teams);
    }
    
    /**
     * Find Teams by minimum number of members.
     *
     * @param minMembers the minimum number of members
     * @return a list of Teams
     */
    @GetMapping("/members")
    @Operation(summary = "Find Teams by minimum members", 
            description = "Returns Teams with at least the specified number of members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found")
    })
    public ResponseEntity<List<TeamResponseDto>> findTeamsByMinMembers(
            @Parameter(description = "Minimum number of members", required = true)
            @RequestParam Integer minMembers) {
        
        List<TeamResponseDto> teams = teamService.findTeamsByMinMembers(minMembers);
        return ResponseEntity.ok(teams);
    }
}