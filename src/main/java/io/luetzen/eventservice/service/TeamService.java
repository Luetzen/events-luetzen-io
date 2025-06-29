package io.luetzen.eventservice.service;

import io.luetzen.eventservice.dto.TeamRequestDto;
import io.luetzen.eventservice.dto.TeamResponseDto;
import io.luetzen.eventservice.exception.ResourceNotFoundException;
import io.luetzen.eventservice.mapper.TeamMapper;
import io.luetzen.eventservice.model.Team;
import io.luetzen.eventservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for managing Team entities.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamService {
    
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    
    /**
     * Create a new Team.
     *
     * @param teamRequestDto the Team data
     * @return the created Team
     */
    public TeamResponseDto createTeam(TeamRequestDto teamRequestDto) {
        Team team = teamMapper.toEntity(teamRequestDto);
        Team savedTeam = teamRepository.save(team);
        
        log.info("Created new Team with ID: {}, Name: {}", savedTeam.getId(), savedTeam.getName());
        return teamMapper.toDto(savedTeam);
    }
    
    /**
     * Get a Team by its ID.
     *
     * @param id the Team ID
     * @return the Team
     * @throws ResourceNotFoundException if the Team is not found
     */
    @Transactional(readOnly = true)
    public TeamResponseDto getTeamById(UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + id));
        
        return teamMapper.toDto(team);
    }
    
    /**
     * Find Teams by name (partial match, case-insensitive).
     *
     * @param name the name to search for
     * @return a list of Teams
     */
    @Transactional(readOnly = true)
    public List<TeamResponseDto> findTeamsByName(String name) {
        List<Team> teams = teamRepository.findByNameContainingIgnoreCase(name);
        return teams.stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Find Teams by minimum number of members.
     *
     * @param minMembers the minimum number of members
     * @return a list of Teams
     */
    @Transactional(readOnly = true)
    public List<TeamResponseDto> findTeamsByMinMembers(Integer minMembers) {
        List<Team> teams = teamRepository.findByMembersGreaterThanEqual(minMembers);
        return teams.stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }
}