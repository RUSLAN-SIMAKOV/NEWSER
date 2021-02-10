package ruslan.simakov.newser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.simakov.newser.model.Sub;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {
}
