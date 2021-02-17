package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.exeption.SpringPostNotFoundException;
import ruslan.simakov.newser.exeption.SpringSubNotFondException;
import ruslan.simakov.newser.mapper.PostMapper;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Sub;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.repository.PostRepository;
import ruslan.simakov.newser.repository.SubRepository;
import ruslan.simakov.newser.repository.UserRepository;
import ruslan.simakov.newser.service.AuthService;
import ruslan.simakov.newser.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthService authService;
    private final SubRepository subRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Override
    public void createPost(PostDto postDto) {
        Sub sub = subRepository.findByName(postDto.getSubName())
                .orElseThrow(() -> new SpringSubNotFondException("Sub not found with name" + postDto.getSubName()));
        User user = authService.getCurrentUser();
        Post post = postMapper.mapDtoToPost(postDto, user, sub);
        postRepository.save(post);
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with id: " + id));
        PostDto postDto = postMapper.mapPostToDto(post);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsBySub(Long id) {
        Sub sub = subRepository.findById(id)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with Sub id: " + id));
        List<PostDto> posts = postRepository.findBySub(sub).stream()
                .map(postMapper::mapPostToDto)
                .collect(Collectors.toList());
        return posts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsByUser(String name) {
        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with username: " + name));
        return postRepository.findByUser(user).stream()
                .map(postMapper::mapPostToDto)
                .collect(Collectors.toList());
    }
}
