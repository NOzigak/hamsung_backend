package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitsRepository extends JpaRepository<Board,Long> {
    //List<Board>findById(Long id);
}
