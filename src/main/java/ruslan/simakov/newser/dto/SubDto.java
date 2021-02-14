package ruslan.simakov.newser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SubDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
