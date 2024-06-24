package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
}
