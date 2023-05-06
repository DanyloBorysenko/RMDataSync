package borysenko.examples.rickandmorty.repository;

import borysenko.examples.rickandmorty.model.MovieCharacter;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByExternalIdIn(Set<Long> ids);

    List<MovieCharacter> findAllByNameContains(String namePart);
}
