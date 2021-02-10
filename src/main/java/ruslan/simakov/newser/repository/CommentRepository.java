package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
