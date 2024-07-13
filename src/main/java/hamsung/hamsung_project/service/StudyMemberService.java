package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.ApplyingSummaryDto;
import hamsung.hamsung_project.dto.ApplyingDto;
import hamsung.hamsung_project.dto.MemberSummaryDto;
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
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyMemberService {

    @Autowired
    private final StudyMemberRepository studyMemberRepository;
    static ReviewRepository reviewRepository;
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
        //아직 승인되지 않은 studyMember만 반환하도록 수정
        List<StudyMember> StudyMemberList = studyMemberRepository.findByStudy_id(study_id);
        List<ApplyingSummaryDto> summaryDtoList=new ArrayList<>();
        for (StudyMember studyMember : StudyMemberList) {
            if (studyMember.getApproval() ==false) {
                summaryDtoList.add(ApplyingSummaryDto.createSummaryDto(studyMember));
            }
        }

//                ApplyingSummaryDto.summaryMembers(applyingList);
        return summaryDtoList;
    }

    //스터디 멤버 승인
    //false=멤버승인X, true=멤버 승인
    //프론트 -> 버튼 비활성화.. ?
    @Transactional
    public boolean approveMember(Long study_id,Long users_id) {
        Study target = studyRepository.findById(study_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디를 찾을 수 없어요."));
//        StudyMember realMember = studyMemberRepository.findByUsersId(users_id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없어요."));
        List<StudyMember> members = studyMemberRepository.findAll();
        StudyMember realMember=null;

        for(StudyMember member:members){
            if(member.getUsers().getId().equals(users_id)&&member.getStudy().getId().equals(study_id)){
                realMember=member;
                break;
            }
        }

        if (realMember.getApproval() == false) {
            realMember.setApproval(true);
            target.setMember_num(target.getMember_num() + 1);
            return true;
        } else {
            realMember.setApproval(true);
            return false;
        }
    }

    //스터디 멤버 조회
    public List<MemberSummaryDto> findAllMembers(Long study_id){
        Study study = studyRepository.findById(study_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 스터디를 찾을 수 없어요."));
        List<StudyMember> StudyMemberList = studyMemberRepository.findByStudy_id(study_id);
        List<MemberSummaryDto> summaryMemberList=new ArrayList<>();
        for (StudyMember studyMember : StudyMemberList) {
            if (studyMember.getApproval() ==true) {
                summaryMemberList.add(MemberSummaryDto.createSummaryDto(studyMember));
            }
        }
        return summaryMemberList;
    }

}