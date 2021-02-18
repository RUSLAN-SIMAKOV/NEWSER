package ruslan.simakov.newser.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Sub;
import ruslan.simakov.newser.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postDto.description")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "sub", source = "sub")
    Post mapDtoToPost(PostDto postDto, User user, Sub sub);

    @Mapping(target = "subName", source = "sub.name")
    @Mapping(target = "userName", source = "user.userName")
    PostDto mapPostToDto(Post post);
}
