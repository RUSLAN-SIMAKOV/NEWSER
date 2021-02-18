package ruslan.simakov.newser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private String text;
    private Long postId;
}
