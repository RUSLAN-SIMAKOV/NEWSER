package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteRepository, Long> {
}
