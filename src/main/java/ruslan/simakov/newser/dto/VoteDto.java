package ruslan.simakov.newser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ruslan.simakov.newser.model.VoteType;

@Data
@AllArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
