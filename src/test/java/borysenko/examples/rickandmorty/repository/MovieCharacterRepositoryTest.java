package borysenko.examples.rickandmorty.repository;

import borysenko.examples.rickandmorty.model.MovieCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.Set;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieCharacterRepositoryTest {
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("rick_and_morty")
            .withUsername("rick_and_morty")
            .withPassword("rick_and_morty");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }
    @Autowired
    MovieCharacterRepository repository;

    @Test
    @Sql("/scripts/insert-two-characters.sql")
    public void findAllByExternalIdIn_OK() {
        List<MovieCharacter> actual = repository.findAllByExternalIdIn(Set.of(1L,2L));
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("bob", actual.get(0).getName());
        Assertions.assertEquals("alice", actual.get(1).getName());
    }

    @Test
    @Sql("/scripts/insert-two-characters.sql")
    public void findAllByNameContains_Ok() {
        List<MovieCharacter> actual = repository.findAllByNameContains("bo");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("bob",actual.get(0).getName());
    }
}