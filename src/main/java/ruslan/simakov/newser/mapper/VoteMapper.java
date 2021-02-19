package ruslan.simakov.newser.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ruslan.simakov.newser.dto.VoteDto;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Vote;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "voteType", source = "voteType")
    @Mapping(target = "post", source = "post")
    Vote mapToVote(VoteDto voteDto, Post post);

    @Mapping(target = "voteId", ignore = true)
    @Mapping(target = "voteType", source = "voteType")
    @Mapping(target = "postId", source = "post.id")
    VoteDto mapToDto(Vote vote);

//    private Long voteId;
//    private VoteType voteType;
//    private Post post;
//    private User user;
//
//    private String voteType;
//    private Long postId;
//    private String username;
}
