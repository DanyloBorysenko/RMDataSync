package borysenko.examples.rickandmorty.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiInfoDto {
    private String next;
    private String prev;
    private int count;
    private int page;
}
