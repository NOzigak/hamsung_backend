package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.ResultDto;
import hamsung.hamsung_project.dto.StudyDto;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudyController {
    @Autowired
    private StudyService studyService;

    @PatchMapping("/study/{id}/end")
    public ResponseEntity<ResultDto<String>> endStudy(@PathVariable Long id){
    boolean result=studyService.endStudy(id);
    if(result){
        return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"스터디 종료 성공"));
    }
    else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "스터디 종료 실패"));
    }
    }

    @GetMapping("/study/{id}")
    public ResponseEntity<?> showStudy(@PathVariable Long id){
        Study study=studyService.showStudy(id);
        if(study!=null){
            StudyDto studyDto= StudyDto.createStudyDto(study);
            return ResponseEntity.status(HttpStatus.OK).body(studyDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "존재하지 않는 스터디입니다."));
        }
    }

    @DeleteMapping("/study/{id}")
    public ResponseEntity<ResultDto<String>> deleteStudy(@PathVariable Long id){
        /*userId가져오도록 시큐리티 구현 후
        Study study=studyService.deleteStudy(id,userId);
        */
        boolean isDeleted=studyService.deleteStudy(id);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디가 삭제되었습니다."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "스터디 삭제에 실패하였습니다."));
    }

    @GetMapping("/study/myStudy/{userId}")
    public ResponseEntity<StudyDto> showMyStudy(@PathVariable Long userId){
        StudyDto target=studyService.showMyStudy(userId);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }


}
