package ruslan.simakov.newser.service;

import ruslan.simakov.newser.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void save(CommentDto commentDto);

    List<CommentDto> getAllCommentsByPost(Long postId);

    List<CommentDto> getAllCommentsByUser(String username);
}
