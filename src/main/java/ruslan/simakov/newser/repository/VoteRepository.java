package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.model.Vote;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByPostAndUser(Post post, User currentUser);
}
