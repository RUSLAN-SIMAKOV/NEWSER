package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
