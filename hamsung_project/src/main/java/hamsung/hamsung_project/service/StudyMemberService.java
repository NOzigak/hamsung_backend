package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.ApplyingSummaryDto;
import hamsung.hamsung_project.dto.ApplyingDto;
import hamsung.hamsung_project.dto.ApplyingSummaryDto;
import hamsung.hamsung_project.dto.StudyMemberDto;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.entity.StudyMember;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.repository.ReviewRepository;
import hamsung.hamsung_project.repository.StudyMemberRepository;
import hamsung.hamsung_project.repository.StudyRepository;
import hamsung.hamsung_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyMemberService {

    @Autowired
    private final StudyMemberRepository studyMemberRepository;
    @Autowired
    private final StudyRepository studyRepository;
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public StudyMember createStudyMember(ApplyingDto applyingdto) {
        Study study = studyRepository.findById(applyingdto.getStudyId())
                .orElseThrow(() -> new RuntimeException("스터디를 찾을 수 없어요."));
        User user = userRepository.findById(applyingdto.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없어요."));

        StudyMember studyMember = new StudyMember();
        studyMember.setStudy(study);
        studyMember.setUsers(user);

        return studyMemberRepository.save(studyMember);
    }

    //신청인원 목록 조회
    @Transactional
    public List<ApplyingSummaryDto> findAllAppliers(Long study_id) {
        Study study = studyRepository.findById(study_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디를 찾을 수 없어요."));

        //studyMemberRepository에서 study_id FK로 찾기->studyMemberList를 applyingSummaryList로 바꿔줘야.
        List<StudyMemberDto> applyingList = studyMemberRepository.findByStudyMember_StudyId(study_id);
        List<ApplyingSummaryDto> target=ApplyingSummaryDto.summaryMembers(applyingList);
        return target;
    }

    //스터디 멤버 승인
    @Transactional
    public boolean approveMember(Long study_id) {
        List<StudyMemberDto> list = studyMemberRepository.findByStudyMember_StudyId(study_id);
        for (StudyMemberDto member : list) {
            if (member.getApproval()==false) {
                member.setApproval(true);
            } else {
                member.setApproval(true);
            }
            //member를 entity로 바꿔줘야. // studymember entity로 바꿔주는 메서드 작성
//            studyMemberRepository.save(member);
//            //study members_num 필드 값 +1
//            //study_id로 study찾고 members_num field get -> +1
//            ResponseEntity<Study>=studyRepository.findById(study_id);


            return member.getApproval();
        }
        return true;

    }

}