package io.luetzen.eventservice.repository;

import io.luetzen.eventservice.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for App entity.
 */
@Repository
public interface AppRepository extends JpaRepository<App, UUID> {
    
    /**
     * Find an app by its Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @return the app if found
     */
    Optional<App> findBySteamAppId(Integer steamAppId);
    
    /**
     * Check if an app exists by its Steam App ID.
     *
     * @param steamAppId the Steam App ID
     * @return true if the app exists
     */
    boolean existsBySteamAppId(Integer steamAppId);
}