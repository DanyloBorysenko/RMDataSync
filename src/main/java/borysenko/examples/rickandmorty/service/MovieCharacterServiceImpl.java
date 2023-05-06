package borysenko.examples.rickandmorty.service;

import borysenko.examples.rickandmorty.dto.api.ApiCharacterDto;
import borysenko.examples.rickandmorty.dto.api.ApiResponseDto;
import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.mapper.MovieCharacterMapper;
import borysenko.examples.rickandmorty.model.MovieCharacter;
import borysenko.examples.rickandmorty.repository.MovieCharacterRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient;
    private final MovieCharacterRepository repository;
    private final MovieCharacterMapper mapper;

    public MovieCharacterServiceImpl(HttpClient httpClient, MovieCharacterRepository repository,
                                     MovieCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Scheduled(cron = "*/30 * * * * ?")
    @Override
    public void syncExternalMovieCharacters() {
        log.info("Method syncExternalMovieCharacters was called at " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = httpClient.get(URL, ApiResponseDto.class);
        saveDtoToDB(apiResponseDto);
        updateMovieCharacter(apiResponseDto);
        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(),
                    ApiResponseDto.class);
            saveDtoToDB(apiResponseDto);
            updateMovieCharacter(apiResponseDto);
        }
    }

    @Override
    public MovieCharacterResponseDto getRandomCharacter() {
        long count = repository.count();
        long randomId = getRandomNumber(count);
        MovieCharacter movieCharacterById = repository.findById(randomId).orElseThrow(() ->
                new NoSuchElementException("Can't find character by id " + randomId));
        return mapper.toResponseDto(movieCharacterById);
    }

    @Override
    public List<MovieCharacterResponseDto> getByNamePart(String namePart) {
        List<MovieCharacter> allByNamePart = repository.findAllByNameContains(namePart);
        return allByNamePart.stream().map(mapper::toResponseDto).collect(Collectors.toList());
    }

    List<MovieCharacter> saveDtoToDB(ApiResponseDto apiResponseDto) {
        if (apiResponseDto == null) {
            throw new RuntimeException("ApiResponseDto can't be null");
        }
        Map<Long, ApiCharacterDto> apiCharacterDtos = Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = apiCharacterDtos.keySet();
        Map<Long, MovieCharacter> movieCharactersFromDb =
                repository.findAllByExternalIdIn(externalIds).stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = movieCharactersFromDb.keySet();
        externalIds.removeAll(existingIds);
        List<MovieCharacter> movieCharactersToSave =
                externalIds.stream().map(apiCharacterDtos::get)
                .map(mapper::parseApiResponseDto)
                .collect(Collectors.toList());
        return repository.saveAll(movieCharactersToSave);
    }

    List<MovieCharacter> updateMovieCharacter(ApiResponseDto apiResponseDto) {
        if (apiResponseDto == null) {
            throw new RuntimeException("ApiResponseDto can't be null");
        }
        Map<Long, MovieCharacter> externalMovieCharactersMap =
                Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, mapper::parseApiResponseDto));
        Set<Long> externalIds = externalMovieCharactersMap.keySet();
        Set<MovieCharacter> existingMovieCharacters =
                new HashSet<>(repository.findAllByExternalIdIn(externalIds));
        Set<MovieCharacter> movieCharactersToUpdate = externalMovieCharactersMap.values().stream()
                .filter(existingMovieCharacters::contains)
                .collect(Collectors.toSet());
        return repository.saveAll(movieCharactersToUpdate);
    }

    private long getRandomNumber(long maxValue) {
        return (long) (Math.random() * maxValue);
    }
}
