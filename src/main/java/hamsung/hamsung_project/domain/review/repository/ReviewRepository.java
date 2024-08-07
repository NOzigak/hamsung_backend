package hamsung.hamsung_project.domain.review.repository;

import hamsung.hamsung_project.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUser_Id(Long user_id);

}
