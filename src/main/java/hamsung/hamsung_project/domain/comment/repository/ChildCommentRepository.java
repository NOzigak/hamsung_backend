package hamsung.hamsung_project.domain.comment.repository;

import hamsung.hamsung_project.domain.comment.entity.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
}
