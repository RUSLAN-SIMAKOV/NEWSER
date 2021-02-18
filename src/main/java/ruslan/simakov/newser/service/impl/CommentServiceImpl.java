package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ruslan.simakov.newser.dto.CommentDto;
import ruslan.simakov.newser.exeption.SpringPostNotFoundException;
import ruslan.simakov.newser.exeption.SpringUserNotFoundException;
import ruslan.simakov.newser.mapper.CommentMapper;
import ruslan.simakov.newser.model.Comment;
import ruslan.simakov.newser.model.NotificationEmail;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.repository.CommentRepository;
import ruslan.simakov.newser.repository.PostRepository;
import ruslan.simakov.newser.repository.UserRepository;
import ruslan.simakov.newser.service.AuthService;
import ruslan.simakov.newser.service.CommentService;
import ruslan.simakov.newser.service.MailService;

import javax.management.Notification;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepository userRepository;

    @Override
    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with id: " + commentDto.getPostId()));
        User user = authService.getCurrentUser();
        Comment comment = commentMapper.mapCommentDtoToComment(commentDto, post, user);
        commentRepository.save(comment);
        sendCommentNotification(post);


    }

    private void sendCommentNotification(Post post) {
        String message = mailContentBuilder.buildEmailText(post.getUser().getUserName() +
                "posted a comment on your post: " + post.getUrl());
        NotificationEmail notificationEmail = new NotificationEmail(post.getUser().getEmail(), message);
        mailService.sendEmail(notificationEmail);
    }

    @Override
    public List<CommentDto> getAllCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with id: " + postId));

        return commentRepository.findByPost(post).stream()
                .map(commentMapper::mapCommentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllCommentsByUser(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new SpringUserNotFoundException("User not found with username :" + username));
        return commentRepository.findByUser(user).stream()
                .map(commentMapper::mapCommentToCommentDto)
                .collect(Collectors.toList());
    }
}
