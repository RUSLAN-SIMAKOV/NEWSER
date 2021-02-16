package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.dto.UserDto;
import ruslan.simakov.newser.exeption.SpringPostNotFoundException;
import ruslan.simakov.newser.exeption.SpringUserNotFoundException;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.repository.PostRepository;
import ruslan.simakov.newser.repository.SubRepository;
import ruslan.simakov.newser.repository.UserRepository;
import ruslan.simakov.newser.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubRepository subRepository;

    @Override
    public void createPost(PostDto postDto) {
        postRepository.save(postDto);
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with id: " + id));
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostDto getPostBySub(Long id) {
        return subRepository.findById(id)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with Sub id: " + id));
    }

    @Override
    public PostDto getPostByUser(String name) {
        return userRepository.findByUserName(name)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with username: " + name));
    }
}
