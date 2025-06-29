package io.luetzen.eventservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating a Team.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDto {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Number of members is required")
    @Min(value = 1, message = "Team must have at least one member")
    private Integer members;
}