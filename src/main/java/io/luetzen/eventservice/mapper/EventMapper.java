package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.EventRequestDto;
import io.luetzen.eventservice.dto.EventResponseDto;
import io.luetzen.eventservice.model.App;
import io.luetzen.eventservice.model.Event;
import org.mapstruct.*;

/**
 * Mapper for Event entity and DTOs.
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class})
public interface EventMapper {
    
    /**
     * Convert Event entity to EventResponseDto.
     *
     * @param event the Event entity
     * @return the EventResponseDto
     */
    EventResponseDto toDto(Event event);
    
    /**
     * Convert EventRequestDto to Event entity.
     * Note: This method requires the App entity to be set separately.
     *
     * @param eventRequestDto the EventRequestDto
     * @return the Event entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "app", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event toEntity(EventRequestDto eventRequestDto);
    
    /**
     * Update Event entity from EventRequestDto.
     * Note: This method requires the App entity to be set separately if it changes.
     *
     * @param eventRequestDto the EventRequestDto
     * @param event the Event entity to update
     * @return the updated Event entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "app", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event updateEntityFromDto(EventRequestDto eventRequestDto, @MappingTarget Event event);
    
    /**
     * Set the App entity in an Event entity.
     *
     * @param event the Event entity
     * @param app the App entity
     * @return the updated Event entity
     */
    @Named("setApp")
    default Event setApp(Event event, App app) {
        event.setApp(app);
        return event;
    }
}