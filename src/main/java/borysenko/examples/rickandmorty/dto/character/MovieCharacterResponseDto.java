package borysenko.examples.rickandmorty.dto.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCharacterResponseDto {
    private Long id;
    private String status;
    private String gender;
    private String name;
}
