package ruslan.simakov.newser.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ruslan.simakov.newser.dto.CommentDto;
import ruslan.simakov.newser.model.Comment;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", source = "post")
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment mapCommentDtoToComment(CommentDto commentDto, Post post, User user);

    @Mapping(target = "text", source = "text")
    @Mapping(target = "postId", source = "post.postId")
    CommentDto mapCommentToCommentDto(Comment comment);
}
