package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.MyStudyDto;
import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.dto.StudyDto;
import hamsung.hamsung_project.entity.Recruit;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.repository.RecruitsRepository;
import hamsung.hamsung_project.entity.StudyMember;
import hamsung.hamsung_project.repository.StudyMemberRepository;
import hamsung.hamsung_project.repository.StudyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudyService {
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    StudyMemberRepository studyMemberRepository;
    @Autowired
    RecruitsRepository recruitsRepository;



    //스터디 생성
    @Transactional
    public StudyDto createStudy(RecruitsRequestsDto requestsDto){
        //엔티티 생성
        Study study=Study.createStudyEntity(requestsDto);
        //엔티티->데이터베이스에 저장
        studyRepository.save(study);
        //requestDto를 studyDto로 변경
        StudyDto target=StudyDto.createStudyDto(study);
        //dto로 변환해 return
        return target;
    }

    public boolean endStudy(Long id){
        Study target=studyRepository.findById(id).orElse(null);
        if(target!=null){
            target.setStatus(false);
            target.setEndDate(LocalDate.now());
            studyRepository.save(target);
            return true;
        }
        return false;
    }

    public Study showStudy(Long id){
        return studyRepository.findById(id).orElse(null);
    }

    /* 로그인 한 userId 가져올 수 있게 시큐리티 구현 후 사용할 스터디 삭제 메소드(아래 껀 삭제하기)
    (스터디 주인의 id와 로그인한 유저의 id가 일치한다면 삭제)
    public boolean deleteStudy(Long id, Long userId){
        Study target=studyRepository.findById(id).orElse(null);

        if(target!=null){
            if(target.getLeader_id().equals(userId)){
                studyRepository.delete(target);
                return true;
            }
        }
        return false;
    }
    */
    public boolean deleteStudy(Long id){
        Study target=studyRepository.findById(id).orElse(null);
        if(target!=null){
            studyRepository.delete(target);
            return true;

        }
        return false;
    }

    /*
    public List<StudyDto> showMyStudy(List<StudyMember> members) {
        studyId를 담을 리스트 생성하기.
        각 studyMember에 대해 getStudyId()해서 얻은 studyId들을 담기
        중복 제거하기
        리스트 내 모든 스터디 아이디에 대해  studyRepository.findById(리스트에 저장된 study id) 해서
        객체 얻고 얘네들 모아 반환하기
    }
    */

    public List<MyStudyDto> showMyStudy(Long userId) {
        //user_id로 userId를 갖는 studyMember 객체 찾기
        List<StudyMember> myList=studyMemberRepository.findByUsers_Id(userId);

        //찾은 studyMember객체의 studyId를 갖는 study 찭기
        List<Long> studyIdList=new ArrayList<>();
        for(StudyMember studyMember:myList){
            if(studyMember.getApproval() &&!studyIdList.contains(studyMember.getStudy().getId())){
                studyIdList.add(studyMember.getStudy().getId());
            }
        }
        List<MyStudyDto> studyDtoList=new ArrayList<>();
        for(Long studyId:studyIdList){
            Study target=studyRepository.findById(studyId).orElse(null);
            Recruit recruit =recruitsRepository.findByStudy_Id(studyId).orElse(null);
            if(target!=null&& recruit !=null){
                studyDtoList.add(MyStudyDto.createMyStudyDto(target, userId, recruit));
            }
            else if(target==null){
                throw new IllegalArgumentException("해당 id의 스터디가 존재하지 않습니다.");
            }
            else{
                throw new IllegalArgumentException("해당 스터디 id의 모집글이 존재하지 않습니다.");
            }
        }
        return studyDtoList;
        }




}
