package io.luetzen.eventservice.repository;

import io.luetzen.eventservice.model.Event;
import io.luetzen.eventservice.model.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repository for Event entity.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    
    /**
     * Find events by app's Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @param pageable pagination information
     * @return a page of events
     */
    @Query("SELECT e FROM Event e JOIN e.app a WHERE a.steamAppId = :steamAppId")
    Page<Event> findByAppSteamAppId(@Param("steamAppId") Integer steamAppId, Pageable pageable);
    
    /**
     * Find events by event type.
     *
     * @param eventType the event type
     * @param pageable pagination information
     * @return a page of events
     */
    Page<Event> findByEventType(EventType eventType, Pageable pageable);
    
    /**
     * Find events that are currently active (current time is between start and end time).
     *
     * @param currentTime the current time
     * @param pageable pagination information
     * @return a page of active events
     */
    @Query("SELECT e FROM Event e WHERE :currentTime BETWEEN e.startTime AND e.endTime")
    Page<Event> findActiveEvents(@Param("currentTime") LocalDateTime currentTime, Pageable pageable);
    
    /**
     * Find upcoming events (start time is after current time).
     *
     * @param currentTime the current time
     * @param pageable pagination information
     * @return a page of upcoming events
     */
    Page<Event> findByStartTimeAfter(LocalDateTime currentTime, Pageable pageable);
}