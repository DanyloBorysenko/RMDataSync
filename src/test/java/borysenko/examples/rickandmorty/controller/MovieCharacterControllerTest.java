package borysenko.examples.rickandmorty.controller;

import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.service.MovieCharacterService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieCharacterControllerTest {
    private MovieCharacterResponseDto dto;
    @MockBean
    private MovieCharacterService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        dto = new MovieCharacterResponseDto();
        dto.setName("bob");
        dto.setId(1L);
    }

    @Test
    void getRandomCharacter() {
        Mockito.when(service.getRandomCharacter()).thenReturn(dto);
        RestAssuredMockMvc.when().get("/movie_characters/randomCharacter")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("bob"));
    }

    @Test
    void getByNamePart() {
        List<MovieCharacterResponseDto> movieCharacterDtoByNamePart = List.of(dto);
        Mockito.when(service.getByNamePart("bo")).thenReturn(movieCharacterDtoByNamePart);
        String namePart= "bo";
        RestAssuredMockMvc.given().queryParam("namePart", namePart).when()
                .get("/movie_characters/by-name").then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("bob"));
    }
}