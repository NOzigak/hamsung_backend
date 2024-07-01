package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.dto.BoardSummaryDto;
import hamsung.hamsung_project.entity.Board;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
//<엔티티로 쓰는 클래스,id타입>
public interface RecruitsRepository extends JpaRepository<Board, Long> {
    //    List<Board> findById(Long id);
    @Transactional
    @Modifying
    @Query("update Board p set p.view=p.view+1 where p.id=:id")
    int updateReadCount(@Param("id")Long id);

    //게시글 전체 조회 //DTO로 조회하기
    @Query("select new hamsung.hamsung_project.dto.BoardSummaryDto(b.id,b.title,b.category,b.place,b.capacity,b.isRecruit,b.createdAt) from Board b ")
    List<BoardSummaryDto> findAllBoardSummaries();

    Optional<Board> findByStudy_Id(Long studyId);

//    @Override
//    public List<BoardSummaryDto> summary(Long id){
//
//    }
}

