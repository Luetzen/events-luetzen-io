package io.luetzen.eventservice.mapper;

import io.luetzen.eventservice.dto.AppRequestDto;
import io.luetzen.eventservice.dto.AppResponseDto;
import io.luetzen.eventservice.model.App;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T11:31:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class AppMapperImpl implements AppMapper {

    @Override
    public AppResponseDto toDto(App app) {
        if ( app == null ) {
            return null;
        }

        AppResponseDto.AppResponseDtoBuilder appResponseDto = AppResponseDto.builder();

        appResponseDto.id( app.getId() );
        appResponseDto.steamAppId( app.getSteamAppId() );
        appResponseDto.name( app.getName() );
        appResponseDto.description( app.getDescription() );
        appResponseDto.createdAt( app.getCreatedAt() );

        return appResponseDto.build();
    }

    @Override
    public App toEntity(AppRequestDto appRequestDto) {
        if ( appRequestDto == null ) {
            return null;
        }

        App.AppBuilder app = App.builder();

        app.steamAppId( appRequestDto.getSteamAppId() );
        app.name( appRequestDto.getName() );
        app.description( appRequestDto.getDescription() );

        return app.build();
    }

    @Override
    public App updateEntityFromDto(AppRequestDto appRequestDto, App app) {
        if ( appRequestDto == null ) {
            return app;
        }

        app.setSteamAppId( appRequestDto.getSteamAppId() );
        app.setName( appRequestDto.getName() );
        app.setDescription( appRequestDto.getDescription() );

        return app;
    }
}
