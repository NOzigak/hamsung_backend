package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {



}
