package io.luetzen.eventservice.service;

import io.luetzen.eventservice.dto.EventRequestDto;
import io.luetzen.eventservice.dto.EventResponseDto;
import io.luetzen.eventservice.exception.ResourceNotFoundException;
import io.luetzen.eventservice.mapper.EventMapper;
import io.luetzen.eventservice.model.App;
import io.luetzen.eventservice.model.Event;
import io.luetzen.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service for managing Event entities.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventService {
    
    private final EventRepository eventRepository;
    private final AppService appService;
    private final EventMapper eventMapper;
    
    /**
     * Create a new Event.
     *
     * @param eventRequestDto the Event data
     * @return the created Event
     * @throws ResourceNotFoundException if the associated App is not found
     */
    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        // Find the associated App
        App app = appService.findAppEntityBySteamAppId(eventRequestDto.getAppSteamId());
        
        // Create and save the Event
        Event event = eventMapper.toEntity(eventRequestDto);
        event = eventMapper.setApp(event, app);
        Event savedEvent = eventRepository.save(event);
        
        log.info("Created new Event with ID: {}, for App: {}", savedEvent.getId(), app.getName());
        return eventMapper.toDto(savedEvent);
    }
    
    /**
     * Get an Event by its ID.
     *
     * @param id the Event ID
     * @return the Event
     * @throws ResourceNotFoundException if the Event is not found
     */
    @Transactional(readOnly = true)
    public EventResponseDto getEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
        
        return eventMapper.toDto(event);
    }
    
    /**
     * Get Events by App's Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @param pageable pagination information
     * @return a page of Events
     */
    @Transactional(readOnly = true)
    public Page<EventResponseDto> getEventsByAppSteamAppId(Integer steamAppId, Pageable pageable) {
        // Check if the App exists
        appService.getAppBySteamAppId(steamAppId);
        
        // Get the Events
        Page<Event> events = eventRepository.findByAppSteamAppId(steamAppId, pageable);
        return events.map(eventMapper::toDto);
    }
}