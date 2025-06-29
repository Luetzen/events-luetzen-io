package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.EventRequestDto;
import io.luetzen.eventservice.dto.EventResponseDto;
import io.luetzen.eventservice.model.Event;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T11:31:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Autowired
    private AppMapper appMapper;

    @Override
    public EventResponseDto toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponseDto.EventResponseDtoBuilder eventResponseDto = EventResponseDto.builder();

        eventResponseDto.id( event.getId() );
        eventResponseDto.app( appMapper.toDto( event.getApp() ) );
        eventResponseDto.name( event.getName() );
        eventResponseDto.description( event.getDescription() );
        eventResponseDto.eventType( event.getEventType() );
        eventResponseDto.startTime( event.getStartTime() );
        eventResponseDto.endTime( event.getEndTime() );
        eventResponseDto.location( event.getLocation() );
        eventResponseDto.metadata( event.getMetadata() );
        eventResponseDto.createdAt( event.getCreatedAt() );

        return eventResponseDto.build();
    }

    @Override
    public Event toEntity(EventRequestDto eventRequestDto) {
        if ( eventRequestDto == null ) {
            return null;
        }

        Event.EventBuilder event = Event.builder();

        event.name( eventRequestDto.getName() );
        event.description( eventRequestDto.getDescription() );
        event.eventType( eventRequestDto.getEventType() );
        event.startTime( eventRequestDto.getStartTime() );
        event.endTime( eventRequestDto.getEndTime() );
        event.location( eventRequestDto.getLocation() );
        event.metadata( eventRequestDto.getMetadata() );

        return event.build();
    }

    @Override
    public Event updateEntityFromDto(EventRequestDto eventRequestDto, Event event) {
        if ( eventRequestDto == null ) {
            return event;
        }

        event.setName( eventRequestDto.getName() );
        event.setDescription( eventRequestDto.getDescription() );
        event.setEventType( eventRequestDto.getEventType() );
        event.setStartTime( eventRequestDto.getStartTime() );
        event.setEndTime( eventRequestDto.getEndTime() );
        event.setLocation( eventRequestDto.getLocation() );
        event.setMetadata( eventRequestDto.getMetadata() );

        return event;
    }
}
