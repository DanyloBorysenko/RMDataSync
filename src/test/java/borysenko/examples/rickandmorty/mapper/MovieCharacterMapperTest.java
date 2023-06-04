package borysenko.examples.rickandmorty.mapper;

import borysenko.examples.rickandmorty.dto.api.ApiCharacterDto;
import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.model.Gender;
import borysenko.examples.rickandmorty.model.MovieCharacter;
import borysenko.examples.rickandmorty.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieCharacterMapperTest {
    private MovieCharacterMapper mapper;
    private MovieCharacter movieCharacter;
    private MovieCharacterResponseDto responseDto;
    private ApiCharacterDto apiCharacterDto;

    @BeforeEach
    void setUp() {
        mapper = new MovieCharacterMapper();
        movieCharacter = new MovieCharacter(1L,"bob", Status.ALIVE, Gender.MALE);
        apiCharacterDto = new ApiCharacterDto(1L,"bob", "ALIVE", "MALE");
        responseDto = new MovieCharacterResponseDto(1L, "ALIVE", "MALE", "bob");
    }

    @Test
    void parseApiResponseDto_Ok() {
        MovieCharacter actual = mapper.parseApiResponseDto(apiCharacterDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(movieCharacter, actual);

    }

    @Test
    void parseApiResponseDto_ApiDtoIsNull_NotOk() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
            mapper.parseApiResponseDto(null);
        });
        Assertions.assertEquals("ApiCharacterDto can't be null", ex.getMessage());
    }

    @Test
    void toResponseDto_Ok() {
        movieCharacter.setId(1L);
        MovieCharacterResponseDto actual = mapper.toResponseDto(movieCharacter);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(responseDto, actual);
    }

    @Test
    void toResponseDto_CharacterIsNull_NotOk() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
            mapper.toResponseDto(null);
        });
        Assertions.assertEquals("MovieCharacter can't be null", ex.getMessage());
    }
}