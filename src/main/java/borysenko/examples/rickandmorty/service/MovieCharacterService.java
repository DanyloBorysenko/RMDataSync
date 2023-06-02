package borysenko.examples.rickandmorty.service;

import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalMovieCharacters();

    MovieCharacterResponseDto getRandomCharacter();

    List<MovieCharacterResponseDto> getByNamePart(String namePart);

    List<MovieCharacterResponseDto> getAll(Integer page, Integer count, String sortBy);
}
