package borysenko.examples.rickandmorty.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "movie_character")
public class MovieCharacter {
    @Id
    @GeneratedValue(generator = "movie_character_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "movie_character_id_seq",
            sequenceName = "movie_character_id_seq", allocationSize = 1)
    private Long id;
    private Long externalId;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public MovieCharacter(Long externalId, String name, Status status, Gender gender) {
        this.externalId = externalId;
        this.name = name;
        this.status = status;
        this.gender = gender;
    }

    public MovieCharacter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieCharacter that = (MovieCharacter) o;
        return Objects.equals(id, that.id)
                && Objects.equals(externalId, that.externalId)
                && Objects.equals(name, that.name) && status == that.status
                && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, name, status, gender);
    }
}
