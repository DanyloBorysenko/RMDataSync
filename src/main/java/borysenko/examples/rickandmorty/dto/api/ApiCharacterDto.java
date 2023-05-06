package borysenko.examples.rickandmorty.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
