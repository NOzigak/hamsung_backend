package hamsung.hamsung_project.domain.manage.service;

import hamsung.hamsung_project.domain.manage.dto.ManageDto;
import hamsung.hamsung_project.domain.manage.entity.Manage;
import hamsung.hamsung_project.domain.study.entity.Study;
import hamsung.hamsung_project.domain.manage.repository.ManageRepository;
import hamsung.hamsung_project.domain.study.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageService {
    @Autowired
    private ManageRepository manageRepository;
    @Autowired
    private StudyRepository studyRepository;

    public ManageDto createWeekScore(ManageDto dto){
        int week=dto.getWeek();
        Long studyId=dto.getStudyId();
        Optional<Manage> manage=manageRepository.findByStudy_IdAndWeek(studyId, week);
        if(manage.isPresent()){
            throw new IllegalArgumentException("이미 해당 스터디 주차에 대한 점수가 기록되어있습니다!");
        }

        int absent=dto.getAbsent();
        int late=dto.getLate();
        int homework=dto.getHomework();

        Study target=studyRepository.findById(dto.getStudyId()).orElseThrow(()->new IllegalArgumentException("해당 스터디가 존재하지 않습니다"));
        int memNum=target.getMember_num();

        int score=50-(absent+(memNum-homework))*10-late*5;
        dto.setWeekScore(score);
        target.setScore(target.getScore()+score); //스터디 점수 업데이트

        Manage created=Manage.createManage(dto,target);
        manageRepository.save(created);

        return dto;

    }


    public List<Manage> getAllWeekManage(Long studyId) {
        studyRepository.findById(studyId).orElseThrow(()->new IllegalArgumentException("해당 id의 스터디가 존재하지 않습니다"));
        List<Manage> manageList=manageRepository.findByStudy_Id(studyId);
        return manageList;
    }

    public Manage getOneWeekManage(Long studyId, int week) {
        return manageRepository.findByStudy_Id(studyId)
                .stream()
                .filter(manage->manage.getWeek()==week)
                .findFirst()
                .orElse(null);
    }
}
