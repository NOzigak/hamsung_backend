package hamsung.hamsung_project.domain.study.controller;

import hamsung.hamsung_project.domain.study.dto.MyStudyDto;
import hamsung.hamsung_project.domain.study.dto.StudyDto;
import hamsung.hamsung_project.domain.study.dto.StudyRankingDto;
import hamsung.hamsung_project.domain.study.entity.Study;
import hamsung.hamsung_project.domain.study.service.StudyService;
import hamsung.hamsung_project.global.config.auth.CustomUserDetails;
import hamsung.hamsung_project.global.util.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

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

/*
    @GetMapping("/study/myStudy/{userId}")
    public ResponseEntity<?> showMyStudy(@PathVariable Long userId){
        List<MyStudyDto> target=studyService.showMyStudy(userId);
        if(target==null){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"현재 가입한 스터디가 없습니다"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }
*/

    @GetMapping("/study/myStudy")
    public ResponseEntity<?> showMyStudy(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        String username=customUserDetails.getUsername();

        List<MyStudyDto> target=studyService.showMyStudy(username);
        if(target==null){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"현재 가입한 스터디가 없습니다"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    //스터디 랭킹(스터디 전체 조회)
    @GetMapping("/study/ranking")
    public ResponseEntity<List<StudyRankingDto>> showRanking(){
        List<StudyRankingDto> rankingList=studyService.showRanking();
        return ResponseEntity.status(HttpStatus.OK).body(rankingList);
    }

}
