package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.ManageDto;
import hamsung.hamsung_project.entity.Manage;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.repository.ManageRepository;
import hamsung.hamsung_project.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageService {
    @Autowired
    private ManageRepository manageRepository;
    @Autowired
    private StudyRepository studyRepository;

    public ManageDto createWeekScore(ManageDto dto){
        int absent=dto.getAbsent();
        int late=dto.getLate();
        int homework=dto.getHomework();

        Study target=studyRepository.findById(dto.getStudyId()).orElseThrow(()->new IllegalArgumentException("해당 스터디가 존재하지 않습니다"));
        int memNum=target.getMember_num();

        Integer score=50-(absent+(memNum-homework))*10-late*5;
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
