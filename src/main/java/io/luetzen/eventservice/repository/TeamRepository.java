package io.luetzen.eventservice.repository;

import io.luetzen.eventservice.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Team entity.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    
    /**
     * Find teams by name containing the given string (case-insensitive).
     *
     * @param name the name to search for
     * @return a list of teams
     */
    List<Team> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find teams by minimum number of members.
     *
     * @param minMembers the minimum number of members
     * @return a list of teams
     */
    List<Team> findByMembersGreaterThanEqual(Integer minMembers);
}