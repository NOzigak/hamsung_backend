package hamsung.hamsung_project.Repository;


import hamsung.hamsung_project.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStudy_Id(Long study_id);
}
