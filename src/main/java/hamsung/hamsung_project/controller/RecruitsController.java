package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.*;
import hamsung.hamsung_project.entity.Recruit;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.service.RecruitsService;
import hamsung.hamsung_project.service.StudyMemberService;
import hamsung.hamsung_project.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitsController {
    @Autowired
    private final RecruitsService recruitsService;
    private final StudyService studyService;
    private final StudyMemberService studyMemberService;


    //게시글 작성 (완료)
    @PostMapping("/recruits")
    public ResponseEntity<RecruitsResponseDto> createRecruit(@RequestBody RecruitsRequestsDto requestsDto) {
        Recruit recruit =recruitsService.createRecruit(requestsDto);

        Study study=Study.createStudyEntity(requestsDto);//studyDto->entity
        RecruitsResponseDto completeRecruitsDto=RecruitsResponseDto.createRecruitsDTO(recruit);
        return ResponseEntity.status(HttpStatus.OK).body(completeRecruitsDto);
    }

    //게시물 전체 목록 조회(완료)
    @GetMapping("/recruits")
    public ResponseEntity<List<RecruitSummaryDto>> showAllRecruits() {
        List<RecruitSummaryDto> recruitList = recruitsService.showAllRecruits();
        return ResponseEntity.status(HttpStatus.OK).body(recruitList);
    }

    //스터디 모집글 수정
    @PutMapping("/recruits/{id}")
    public ResponseEntity<ResultDto<String>> updateRecruits(@PathVariable Long id, @RequestBody RecruitsRequestsDto requestsDto) {
        RecruitsResponseDto updatedRecruits = recruitsService.updateRecruits(id, requestsDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"수정 완료하였습니다."));
    }


    //스터디 모집상태 변경+스터디 startDate 설정(모집완료로변경시)+
    @PutMapping("recruits/{id}/isrecruit")
    public ResponseEntity<ResultDto<String>> changeStatus(@PathVariable Long id) {
        boolean isFinished = recruitsService.changeIsRecruit(id);
        if (isFinished) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디를 모집완료 상태로 변경합니다(모집중->모집완료)"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디를 모집중 상태로 변경합니다(모집완료->모집중)"));
        }
    }

    //스터디 모집글 상세 조회+조회수 증가 (완료)
    @GetMapping("/recruits/{id}")
    public ResponseEntity<RecruitsResponseDto> getAPost(@PathVariable Long id) {
        RecruitsResponseDto aRecruit = recruitsService.getAPost(id);
        recruitsService.updateReadCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(aRecruit);
    }

    //게시글 삭제 (완료)
    @DeleteMapping("recruits/{id}")
    public ResponseEntity<ResultDto<String>> deleteRecruit(@PathVariable Long id) {
        boolean isDeleted = recruitsService.deleteRecruit(id);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "게시글이 삭제되었습니다."));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "게시글 삭제에 실패하였습니다."));}
    }


    //스터디 지원하기
    @PostMapping("recruits/{study_id}/members")
    public ResponseEntity<ResultDto<String>> applyStudy(@PathVariable Long study_id, @RequestBody ApplyingDto applyingDto) {
        boolean isApplied = recruitsService.applyStudy(study_id, applyingDto);
        //스터디 id 가져오기
        //user-userid,nickname & review 통채로 가져오기
        if (isApplied) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디에 지원완료했습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "스터디 지원에 실패하였습니다. 다시 시도해주세요."));
        }
    }


    //스터디 신청 인원 조회(postman test아직X , user 등록 후 다시 해보기)
    @GetMapping("recruits/{study_id}/members")
    public ResponseEntity<List<ApplyingSummaryDto>> findAllAppliers(@PathVariable Long study_id) {
        try {
            List<ApplyingSummaryDto> appliedList = studyMemberService.findAllAppliers(study_id);
            return ResponseEntity.status(HttpStatus.OK).body(appliedList);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//            List<ApplySummaryDto> appliedList=studyMemberService.findAllAppliers(study_id);
//            return ResponseEntity.status(HttpStatus.OK).body(appliedList);
    }


    //스터디 멤버 승인
    @PatchMapping("recruits/{study_id}/members/{users_id}")
    public ResponseEntity<ResultDto<String>> approveMember(@PathVariable Long study_id,@PathVariable Long users_id) {
        boolean isApproved = studyMemberService.approveMember(study_id,users_id);
        if (isApproved) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "승인완료하였습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "이미 승인 완료된 지원자입니다."));
        }


    }
}