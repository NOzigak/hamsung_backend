package hamsung.hamsung_project.domain.study.service;


import hamsung.hamsung_project.domain.study.dto.MyStudyDto;
import hamsung.hamsung_project.domain.recruit.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.domain.study.dto.StudyDto;
import hamsung.hamsung_project.domain.study.dto.StudyRankingDto;
import hamsung.hamsung_project.domain.study.entity.Study;
import hamsung.hamsung_project.domain.recruit.repository.RecruitsRepository;
import hamsung.hamsung_project.domain.studymember.entity.StudyMember;
import hamsung.hamsung_project.domain.studymember.repository.StudyMemberRepository;
import hamsung.hamsung_project.domain.study.repository.StudyRepository;
import hamsung.hamsung_project.global.exception.InvalidDataException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public StudyDto createStudy(RecruitsRequestsDto requestsDto) {
        //엔티티 생성
        Study study = Study.createStudyEntity(requestsDto);
        //엔티티->데이터베이스에 저장
        studyRepository.save(study);
        //requestDto를 studyDto로 변경
        StudyDto target = StudyDto.createStudyDto(study);
        //dto로 변환해 return
        return target;
    }

    public boolean endStudy(Long id) {
        Study target = studyRepository.findById(id).orElse(null);
        if (target != null) {
            target.setStatus(false);
            target.setEndDate(LocalDate.now());
            studyRepository.save(target);
            return true;
        }
        return false;
    }

    public Study showStudy(Long id) {

        return studyRepository.findById(id).orElse(null);
    }

    public boolean deleteStudy(Long id) {
        Study target = studyRepository.findById(id).orElse(null);
        if (target != null) {
            studyRepository.delete(target);
            return true;

        }
        return false;
    }


    public List<MyStudyDto> showMyStudy(Long userId) {
        //user_id로 userId를 갖는 studyMember 객체 찾기
        List<StudyMember> myList = studyMemberRepository.findByUsers_Id(userId);

        //찾은 studyMember객체의 studyId를 갖는 study 찭기
        List<Long> studyIdList = new ArrayList<>();
        for (StudyMember studyMember : myList) {
            if (studyMember.getApproval() && !studyIdList.contains(studyMember.getStudy().getId())) {
                studyIdList.add(studyMember.getStudy().getId());
            }
        }
        List<MyStudyDto> studyDtoList = new ArrayList<>();
        for (Long studyId : studyIdList) {
            Study target = studyRepository.findById(studyId).orElse(null);

             if(target!=null){
                 studyDtoList.add(MyStudyDto.createMyStudyDto(target, userId));
             }else {
                 throw new InvalidDataException("해당 id의 스터디가 존재하지 않습니다.");
             }

        }
        return studyDtoList;
    }

    //스터디 랭킹(스터디 전체 조회)
    @Transactional
    public List<StudyRankingDto> showRanking() {
        List<Study> studyList = studyRepository.findAll();
//        List<StudyRankingDto> ranking=new ArrayList<>();
        return studyList.stream()
                .filter(study -> study.getScore() != 0)
                .sorted((m1, m2) -> Integer.compare(m2.getScore(), m1.getScore()))
                .limit(10)
                .map(study -> new StudyRankingDto(
                        study.getId(),
                        study.getTitle(),
                        study.getScore()
                ))
                .collect(Collectors.toList());


    }
}