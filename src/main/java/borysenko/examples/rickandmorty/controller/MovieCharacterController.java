package borysenko.examples.rickandmorty.controller;

import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.service.MovieCharacterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/movie_characters")
public class MovieCharacterController {
    private final MovieCharacterService service;

    public MovieCharacterController(MovieCharacterService service) {
        this.service = service;
    }

    @GetMapping("/randomCharacter")
    public MovieCharacterResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/by-name")
    public List<MovieCharacterResponseDto> getByNamePart(@RequestParam String namePart) {
        return service.getByNamePart(namePart);
    }
}
