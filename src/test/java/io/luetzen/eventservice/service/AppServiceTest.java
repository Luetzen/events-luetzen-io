package io.luetzen.eventservice.service;

import io.luetzen.eventservice.dto.AppRequestDto;
import io.luetzen.eventservice.dto.AppResponseDto;
import io.luetzen.eventservice.exception.ResourceAlreadyExistsException;
import io.luetzen.eventservice.exception.ResourceNotFoundException;
import io.luetzen.eventservice.mapper.AppMapper;
import io.luetzen.eventservice.model.App;
import io.luetzen.eventservice.repository.AppRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @Mock
    private AppRepository appRepository;

    @Mock
    private AppMapper appMapper;

    @InjectMocks
    private AppService appService;

    private AppRequestDto appRequestDto;
    private App app;
    private AppResponseDto appResponseDto;
    private UUID appId;
    private Integer steamAppId;

    @BeforeEach
    void setUp() {
        appId = UUID.randomUUID();
        steamAppId = 570;
        
        appRequestDto = AppRequestDto.builder()
                .steamAppId(steamAppId)
                .name("Dota 2")
                .description("A popular MOBA game")
                .build();
        
        app = App.builder()
                .id(appId)
                .steamAppId(steamAppId)
                .name("Dota 2")
                .description("A popular MOBA game")
                .createdAt(LocalDateTime.now())
                .build();
        
        appResponseDto = AppResponseDto.builder()
                .id(appId)
                .steamAppId(steamAppId)
                .name("Dota 2")
                .description("A popular MOBA game")
                .createdAt(app.getCreatedAt())
                .build();
    }

    @Test
    void createApp_Success() {
        // Arrange
        when(appRepository.existsBySteamAppId(steamAppId)).thenReturn(false);
        when(appMapper.toEntity(appRequestDto)).thenReturn(app);
        when(appRepository.save(app)).thenReturn(app);
        when(appMapper.toDto(app)).thenReturn(appResponseDto);

        // Act
        AppResponseDto result = appService.createApp(appRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(appId, result.getId());
        assertEquals(steamAppId, result.getSteamAppId());
        assertEquals("Dota 2", result.getName());
        
        verify(appRepository).existsBySteamAppId(steamAppId);
        verify(appMapper).toEntity(appRequestDto);
        verify(appRepository).save(app);
        verify(appMapper).toDto(app);
    }

    @Test
    void createApp_AppAlreadyExists() {
        // Arrange
        when(appRepository.existsBySteamAppId(steamAppId)).thenReturn(true);

        // Act & Assert
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            appService.createApp(appRequestDto);
        });
        
        verify(appRepository).existsBySteamAppId(steamAppId);
        verify(appMapper, never()).toEntity(any());
        verify(appRepository, never()).save(any());
    }

    @Test
    void getAppById_Success() {
        // Arrange
        when(appRepository.findById(appId)).thenReturn(Optional.of(app));
        when(appMapper.toDto(app)).thenReturn(appResponseDto);

        // Act
        AppResponseDto result = appService.getAppById(appId);

        // Assert
        assertNotNull(result);
        assertEquals(appId, result.getId());
        assertEquals(steamAppId, result.getSteamAppId());
        
        verify(appRepository).findById(appId);
        verify(appMapper).toDto(app);
    }

    @Test
    void getAppById_NotFound() {
        // Arrange
        when(appRepository.findById(appId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            appService.getAppById(appId);
        });
        
        verify(appRepository).findById(appId);
        verify(appMapper, never()).toDto(any());
    }

    @Test
    void getAppBySteamAppId_Success() {
        // Arrange
        when(appRepository.findBySteamAppId(steamAppId)).thenReturn(Optional.of(app));
        when(appMapper.toDto(app)).thenReturn(appResponseDto);

        // Act
        AppResponseDto result = appService.getAppBySteamAppId(steamAppId);

        // Assert
        assertNotNull(result);
        assertEquals(appId, result.getId());
        assertEquals(steamAppId, result.getSteamAppId());
        
        verify(appRepository).findBySteamAppId(steamAppId);
        verify(appMapper).toDto(app);
    }

    @Test
    void getAppBySteamAppId_NotFound() {
        // Arrange
        when(appRepository.findBySteamAppId(steamAppId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            appService.getAppBySteamAppId(steamAppId);
        });
        
        verify(appRepository).findBySteamAppId(steamAppId);
        verify(appMapper, never()).toDto(any());
    }

    @Test
    void findAppEntityBySteamAppId_Success() {
        // Arrange
        when(appRepository.findBySteamAppId(steamAppId)).thenReturn(Optional.of(app));

        // Act
        App result = appService.findAppEntityBySteamAppId(steamAppId);

        // Assert
        assertNotNull(result);
        assertEquals(appId, result.getId());
        assertEquals(steamAppId, result.getSteamAppId());
        
        verify(appRepository).findBySteamAppId(steamAppId);
    }

    @Test
    void findAppEntityBySteamAppId_NotFound() {
        // Arrange
        when(appRepository.findBySteamAppId(steamAppId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            appService.findAppEntityBySteamAppId(steamAppId);
        });
        
        verify(appRepository).findBySteamAppId(steamAppId);
    }
}