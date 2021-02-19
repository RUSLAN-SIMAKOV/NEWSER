package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ruslan.simakov.newser.dto.VoteDto;
import ruslan.simakov.newser.exeption.SpringPostNotFoundException;
import ruslan.simakov.newser.mapper.VoteMapper;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Vote;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.model.VoteType;
import ruslan.simakov.newser.repository.PostRepository;
import ruslan.simakov.newser.repository.VoteRepository;
import ruslan.simakov.newser.service.AuthService;
import ruslan.simakov.newser.service.VoteService;

import java.util.Optional;



@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final VoteMapper voteMapper;
    private final AuthService authService;

    @Override
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new SpringPostNotFoundException("Post not found with id: " + voteDto.getPostId()));
        User currentUser = authService.getCurrentUser();
        Optional<Vote> voteByPostAndUser = voteRepository.findByPostAndUser(post, currentUser);
        VoteType voteTypeFromUser = voteDto.getVoteType();
        boolean isUserWantUnVote = voteByPostAndUser.isPresent();
        if (isUserWantUnVote && voteByPostAndUser.get().getVoteType().equals(voteTypeFromUser)) {
            if (VoteType.UPVOTE.equals(voteTypeFromUser)) {
                post.setVoteCount(post.getVoteCount() - 1);
            } else {
                post.setVoteCount(post.getVoteCount() + 1);
            }
        } else {
            if (VoteType.UPVOTE.equals(voteTypeFromUser)) {
                post.setVoteCount(post.getVoteCount() + 1);
            } else {
                post.setVoteCount(post.getVoteCount() - 1);
            }
        }
        voteRepository.save(voteMapper.mapToVote(voteDto, post));
        postRepository.save(post);
    }
}
