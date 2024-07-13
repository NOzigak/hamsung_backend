package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Manage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManageRepository extends JpaRepository<Manage, Long> {
    List<Manage> findByStudy_Id(Long studyId);
    Optional<Manage> findByStudy_IdAndWeek(Long studyId, int week);
}
