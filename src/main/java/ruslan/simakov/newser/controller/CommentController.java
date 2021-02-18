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
import ruslan.simakov.newser.dto.CommentDto;
import ruslan.simakov.newser.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/bypost/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(commentService.getAllCommentsByPost(postId));
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.FOUND).body(commentService.getAllCommentsByUser(username));
    }
}
