package hamsung.hamsung_project.domain.user.repository;

import hamsung.hamsung_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByUsername(String username);

    User findByEmail(String email);

}
