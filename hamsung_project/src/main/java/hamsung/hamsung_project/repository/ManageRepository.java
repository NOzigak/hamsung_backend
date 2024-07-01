package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Manage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManageRepository extends JpaRepository<Manage, Long> {
    List<Manage> findByStudy_Id(Long studyId);
}
