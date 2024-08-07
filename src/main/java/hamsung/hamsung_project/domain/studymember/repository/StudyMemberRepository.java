package hamsung.hamsung_project.domain.studymember.repository;

import hamsung.hamsung_project.domain.studymember.entity.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember,Long> {
    // 신청한 인원 전체 조회
// studyMemberRepository에서 study_id FK로 찾기
//    @Query("select new hamsung.hamsung_project.domain.studymember.dto.StudyMemberDto(s.users.id, s.users.username, s.users.review) " +
//            "from StudyMember s where s.study.id = :study_id")
//    List<StudyMemberDto> findByStudyMember_StudyId(@Param("study_id") Long study_id);
    List<StudyMember> findByStudy_Id(Long study_id);
    List<StudyMember> findByUsers_Id(Long user_id);
    Optional<StudyMember> findByUsersId(Long users_id);
}



////신청한 인원 전체 조회
////studyMemberRepository에서 study_id FK로 찾기
//@Query("select new hamsung.hamsung_project.domain.studymember.dto.StudyMemberDto(s.users.id,s.users.username ,s.review) from StudyMember s")
//List<StudyMemberDto> findByStudyMember_StudyId(Long study_id);