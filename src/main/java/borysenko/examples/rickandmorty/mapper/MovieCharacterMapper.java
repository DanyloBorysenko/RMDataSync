package borysenko.examples.rickandmorty.mapper;

import borysenko.examples.rickandmorty.dto.api.ApiCharacterDto;
import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.model.Gender;
import borysenko.examples.rickandmorty.model.MovieCharacter;
import borysenko.examples.rickandmorty.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiResponseDto(ApiCharacterDto dto) {
        if (dto == null) {
            throw new RuntimeException("ApiCharacterDto can't be null");
        }
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public MovieCharacterResponseDto toResponseDto(MovieCharacter movieCharacter) {
        if (movieCharacter == null) {
            throw new RuntimeException("MovieCharacter can't be null");
        }
        MovieCharacterResponseDto dto = new MovieCharacterResponseDto();
        dto.setGender(movieCharacter.getGender().name());
        dto.setId(movieCharacter.getId());
        dto.setStatus(movieCharacter.getStatus().name());
        dto.setName(movieCharacter.getName());
        return dto;
    }
}
