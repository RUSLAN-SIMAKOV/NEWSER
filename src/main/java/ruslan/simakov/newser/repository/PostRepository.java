package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.dto.PostDto;
import ruslan.simakov.newser.model.Post;
import ruslan.simakov.newser.model.Sub;
import ruslan.simakov.newser.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findBySub(Sub sub);

    List<Post> findByUser(User user);
}
