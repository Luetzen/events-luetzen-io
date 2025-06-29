package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.AppRequestDto;
import io.luetzen.eventservice.dto.AppResponseDto;
import io.luetzen.eventservice.model.App;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for App entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface AppMapper {
    
    /**
     * Convert App entity to AppResponseDto.
     *
     * @param app the App entity
     * @return the AppResponseDto
     */
    AppResponseDto toDto(App app);
    
    /**
     * Convert AppRequestDto to App entity.
     *
     * @param appRequestDto the AppRequestDto
     * @return the App entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    App toEntity(AppRequestDto appRequestDto);
    
    /**
     * Update App entity from AppRequestDto.
     *
     * @param appRequestDto the AppRequestDto
     * @param app the App entity to update
     * @return the updated App entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    App updateEntityFromDto(AppRequestDto appRequestDto, @MappingTarget App app);
}