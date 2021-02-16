package ruslan.simakov.newser.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Sub;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(sub.getPosts()))")
    SubDto mapSubToDto(Sub sub);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Sub mapDtoToSub(SubDto subDto);
}
