package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.TeamRequestDto;
import io.luetzen.eventservice.dto.TeamResponseDto;
import io.luetzen.eventservice.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for Team entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {
    
    /**
     * Convert Team entity to TeamResponseDto.
     *
     * @param team the Team entity
     * @return the TeamResponseDto
     */
    TeamResponseDto toDto(Team team);
    
    /**
     * Convert TeamRequestDto to Team entity.
     *
     * @param teamRequestDto the TeamRequestDto
     * @return the Team entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Team toEntity(TeamRequestDto teamRequestDto);
    
    /**
     * Update Team entity from TeamRequestDto.
     *
     * @param teamRequestDto the TeamRequestDto
     * @param team the Team entity to update
     * @return the updated Team entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Team updateEntityFromDto(TeamRequestDto teamRequestDto, @MappingTarget Team team);
}