package ruslan.simakov.newser.service;

import org.springframework.stereotype.Service;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.dto.UserDto;

import java.util.List;


public interface PostService {

     void createPost(PostDto postDto);

     PostDto getPost(Long id);

     List<PostDto> getAllPosts();

     List<PostDto> getPostsBySub(Long id);

     List<PostDto> getPostsByUser(String name);

}
