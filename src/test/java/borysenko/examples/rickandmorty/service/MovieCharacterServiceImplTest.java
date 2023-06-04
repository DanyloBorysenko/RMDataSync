package borysenko.examples.rickandmorty.service;

import borysenko.examples.rickandmorty.dto.api.ApiCharacterDto;
import borysenko.examples.rickandmorty.dto.api.ApiResponseDto;
import borysenko.examples.rickandmorty.mapper.MovieCharacterMapper;
import borysenko.examples.rickandmorty.model.Gender;
import borysenko.examples.rickandmorty.model.MovieCharacter;
import borysenko.examples.rickandmorty.model.Status;
import borysenko.examples.rickandmorty.repository.MovieCharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MovieCharacterServiceImplTest {
    @InjectMocks
    private MovieCharacterServiceImpl service;
    @Mock
    private MovieCharacterRepository mockRepository;
    @Mock
    private MovieCharacterMapper mapperMock;
    private ApiResponseDto apiResponseDto;
    private ApiCharacterDto apiCharacterDto;
    private MovieCharacter movieCharacter;

    @BeforeEach
    void setUp() {
        apiCharacterDto = new ApiCharacterDto(1L,"bob", "ALIVE", "Male");
        apiResponseDto = new ApiResponseDto();
        apiResponseDto.setInfo(null);
        apiResponseDto.setResults(new ApiCharacterDto[]{apiCharacterDto});
        movieCharacter = new MovieCharacter(1L,"bob", Status.ALIVE, Gender.MALE);
    }

    @Test
    void saveDtoToDB() {
        Mockito.when(mockRepository.findAllByExternalIdIn(any()))
                .thenReturn(Collections.emptyList());
        Mockito.when(mapperMock.parseApiResponseDto(apiCharacterDto)).thenReturn(movieCharacter);
        Mockito.when(mockRepository.saveAll(List.of(movieCharacter))).thenReturn(List.of(movieCharacter));
        List<MovieCharacter> actual = service.saveDtoToDB(apiResponseDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("bob", actual.get(0).getName());
    }

    @Test
    void saveDtoToDB_ApiResponseDtoIsNull_NotOk() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.saveDtoToDB(null);
        });
        Assertions.assertEquals("ApiResponseDto can't be null", ex.getMessage());
    }

    @Test
    void updateMovieCharacter_Ok() {
        Mockito.when(mapperMock.parseApiResponseDto(apiCharacterDto)).thenReturn(movieCharacter);
        Mockito.when(mockRepository.findAllByExternalIdIn(any()))
                .thenReturn(List.of(movieCharacter));
        Mockito.when(mockRepository.saveAll(Set.of(movieCharacter))).thenReturn(List.of(movieCharacter));
        List<MovieCharacter> actual = service.updateMovieCharacter(apiResponseDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("bob", actual.get(0).getName());
    }

    @Test
    void updateMovieCharacter_ApiResponseDtoIsNull_NotOk() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.saveDtoToDB(null);
        });
        Assertions.assertEquals("ApiResponseDto can't be null", ex.getMessage());
    }
}