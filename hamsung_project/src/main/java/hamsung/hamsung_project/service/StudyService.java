package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.dto.StudyDto;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.repository.StudyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudyService {
    @Autowired
    StudyRepository studyRepository;


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

    public StudyDto showMyStudy(Long userId) {
        return null;
    }
}
