package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.ManageDto;
import hamsung.hamsung_project.dto.ResultDto;
import hamsung.hamsung_project.entity.Manage;
import hamsung.hamsung_project.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageController {

    @Autowired
    private ManageService manageService;

    @PostMapping("study/{studyId}/manage")
    public ResponseEntity<?> createWeekScore(@PathVariable Long studyId, @RequestBody ManageDto dto){
        dto.setStudyId(studyId);
        ManageDto createdDto=manageService.createWeekScore(dto);
        if(createdDto!=null){
            return ResponseEntity.ok(createdDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(),"주차기록에 실패"));
    }

    @GetMapping("study/{studyId}/manage")
    public ResponseEntity<?> getAllWeekManage(@PathVariable Long studyId){
        List<Manage> manageList=manageService.getAllWeekManage(studyId);
        if(manageList.isEmpty()){
            return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK.toString(),"기록된 스터디 주차별 관리 기록이 없습니다"));
        }
        return ResponseEntity.ok(manageList);
    }

    @GetMapping("/study/{studyId}/manage/{week}")
    public ResponseEntity<?> getOneWeekManage(@PathVariable Long studyId, @PathVariable int week){
        Manage manage=manageService.getOneWeekManage(studyId,week);
        if(manage==null){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"해당 주차의 기록이 없습니다"));
        }
        return ResponseEntity.ok(manage);
    }

}
