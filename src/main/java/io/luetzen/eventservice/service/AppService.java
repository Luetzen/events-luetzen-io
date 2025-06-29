package io.luetzen.eventservice.service;

import io.luetzen.eventservice.dto.AppRequestDto;
import io.luetzen.eventservice.dto.AppResponseDto;
import io.luetzen.eventservice.exception.ResourceAlreadyExistsException;
import io.luetzen.eventservice.exception.ResourceNotFoundException;
import io.luetzen.eventservice.mapper.AppMapper;
import io.luetzen.eventservice.model.App;
import io.luetzen.eventservice.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service for managing App entities.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AppService {
    
    private final AppRepository appRepository;
    private final AppMapper appMapper;
    
    /**
     * Create a new App.
     *
     * @param appRequestDto the App data
     * @return the created App
     * @throws ResourceAlreadyExistsException if an App with the same Steam App ID already exists
     */
    public AppResponseDto createApp(AppRequestDto appRequestDto) {
        if (appRepository.existsBySteamAppId(appRequestDto.getSteamAppId())) {
            throw new ResourceAlreadyExistsException("App with Steam App ID " + appRequestDto.getSteamAppId() + " already exists");
        }
        
        App app = appMapper.toEntity(appRequestDto);
        App savedApp = appRepository.save(app);
        
        log.info("Created new App with ID: {}, Steam App ID: {}", savedApp.getId(), savedApp.getSteamAppId());
        return appMapper.toDto(savedApp);
    }
    
    /**
     * Get an App by its ID.
     *
     * @param id the App ID
     * @return the App
     * @throws ResourceNotFoundException if the App is not found
     */
    @Transactional(readOnly = true)
    public AppResponseDto getAppById(UUID id) {
        App app = appRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("App not found with ID: " + id));
        
        return appMapper.toDto(app);
    }
    
    /**
     * Get an App by its Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @return the App
     * @throws ResourceNotFoundException if the App is not found
     */
    @Transactional(readOnly = true)
    public AppResponseDto getAppBySteamAppId(Integer steamAppId) {
        App app = appRepository.findBySteamAppId(steamAppId)
                .orElseThrow(() -> new ResourceNotFoundException("App not found with Steam App ID: " + steamAppId));
        
        return appMapper.toDto(app);
    }
    
    /**
     * Find an App by its Steam App ID or throw an exception if not found.
     * This method is for internal use by other services.
     *
     * @param steamAppId the Steam App ID
     * @return the App entity
     * @throws ResourceNotFoundException if the App is not found
     */
    @Transactional(readOnly = true)
    public App findAppEntityBySteamAppId(Integer steamAppId) {
        return appRepository.findBySteamAppId(steamAppId)
                .orElseThrow(() -> new ResourceNotFoundException("App not found with Steam App ID: " + steamAppId));
    }
}