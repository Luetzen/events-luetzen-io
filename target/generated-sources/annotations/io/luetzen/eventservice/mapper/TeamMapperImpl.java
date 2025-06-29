package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.TeamRequestDto;
import io.luetzen.eventservice.dto.TeamResponseDto;
import io.luetzen.eventservice.model.Team;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T11:31:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamResponseDto toDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamResponseDto.TeamResponseDtoBuilder teamResponseDto = TeamResponseDto.builder();

        teamResponseDto.id( team.getId() );
        teamResponseDto.name( team.getName() );
        teamResponseDto.members( team.getMembers() );
        teamResponseDto.createdAt( team.getCreatedAt() );

        return teamResponseDto.build();
    }

    @Override
    public Team toEntity(TeamRequestDto teamRequestDto) {
        if ( teamRequestDto == null ) {
            return null;
        }

        Team.TeamBuilder team = Team.builder();

        team.name( teamRequestDto.getName() );
        team.members( teamRequestDto.getMembers() );

        return team.build();
    }

    @Override
    public Team updateEntityFromDto(TeamRequestDto teamRequestDto, Team team) {
        if ( teamRequestDto == null ) {
            return team;
        }

        team.setName( teamRequestDto.getName() );
        team.setMembers( teamRequestDto.getMembers() );

        return team;
    }
}
