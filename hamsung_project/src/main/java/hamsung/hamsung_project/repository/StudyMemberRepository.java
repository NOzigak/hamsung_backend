package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.dto.StudyMemberDto;
import hamsung.hamsung_project.entity.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember,Long> {
    //신청한 인원 전체 조회
    //studyMemberRepository에서 study_id FK로 찾기
//    @Query("select new hamsung.hamsung_project.dto.ApplySummaryDto(s.user ,s.review) from StudyMember s")
//    List<StudyMemberDto> findByStudyMember_StudyId(Long study_id);

}
