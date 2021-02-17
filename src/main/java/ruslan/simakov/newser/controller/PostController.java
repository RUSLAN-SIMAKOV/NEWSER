package ruslan.simakov.newser.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable Long id) {
        PostDto postDto = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(postDto);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(postService.getAllPosts());
    }

    @GetMapping("/getbysub/{id}")
    public ResponseEntity<List<PostDto>> getPostsBySub(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(postService.getPostsBySub(id));
    }

    @GetMapping("/getbyuser/{name}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(postService.getPostsByUser(name));
    }
}
