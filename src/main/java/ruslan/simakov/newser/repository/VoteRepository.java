package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
