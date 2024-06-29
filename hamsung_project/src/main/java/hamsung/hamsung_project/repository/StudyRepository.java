package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {


}
