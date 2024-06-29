package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.BoardSummaryDto;
import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.dto.RecruitsResponseDto;
import hamsung.hamsung_project.dto.ResultDto;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.service.RecruitsService;
import hamsung.hamsung_project.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitsController {
    private final RecruitsService recruitsService;
    private final StudyService studyService;
//        private final StudyMemberService studyMemberService;


    //게시글 작성 (완료)
    @PostMapping("/recruits")
    public ResponseEntity<RecruitsResponseDto> createRecruit(@RequestBody RecruitsRequestsDto requestsDto){
        RecruitsResponseDto createdDTO=recruitsService.createRecruit(requestsDto);
        studyService.createStudy(requestsDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
    }

    //게시물 전체 목록 조회-writer 추가해야(닉네임?)
    @GetMapping("/recruits")
    public ResponseEntity<List<BoardSummaryDto>> showAllRecruits(){
        List<BoardSummaryDto> boardList= recruitsService.showAllRecruits();
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    //스터디 모집글 수정-에러 500뜸.. 아직...
    @PutMapping("/recruits/{id}")
    public ResponseEntity<RecruitsResponseDto> updateRecruits(@PathVariable Long id, @RequestBody RecruitsRequestsDto requestsDto) {
        RecruitsResponseDto updatedRecruits=recruitsService.updateRecruits(id,requestsDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRecruits);
    }


    //스터디 모집상태 변경 - status가 아니라 isRecruit 로 변경해야
    //스터디 엔티티 안에
    @PutMapping("recruits/{id}/status")
    public ResponseEntity<ResultDto<String>> changeStatus(@PathVariable Long id){
        boolean isFinished=recruitsService.changeStatus(id);
        if(isFinished){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"스터디를 모집완료 상태로 변경합니다(모집중->모집완료)"));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"스터디를 모집중 상태로 변경합니다(모집완료->모집중)"));
        }
    }

    //스터디 모집글 상세 조회+조회수 증가 (완료)
    @GetMapping("/recruits/{id}")
    public ResponseEntity<RecruitsResponseDto> getAPost(@PathVariable Long id){
        RecruitsResponseDto aRecruit= recruitsService.getAPost(id);
        recruitsService.updateReadCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(aRecruit);
    }

    //게시글 삭제 (완료)
    @DeleteMapping("recruits/{id}")
    public ResponseEntity<ResultDto<String>> deletePost(@PathVariable Long id){
        boolean isDeleted=recruitsService.deletePost(id);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"게시글이 삭제되었습니다."));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(),"게시글 삭제에 실패하였습니다."));
    }


    //스터디 지원하기(postman test아직X , user 등록 후 다시 해보기)
    @PostMapping("recruits/{study_id}/members")
    public ResponseEntity<ResultDto<String>> applyStudy(@PathVariable Long id,@RequestBody ApplyingDto applyingDto) {
        boolean isApplied = recruitsService.applyStudy(id,applyingDto);
        //스터디 id 가져오기
        //user-userid,nickname & review 통채로 가져오기
        if (isApplied) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디에 지원완료했습니다."));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(),"스터디 지원에 실패하였습니다. 다시 시도해주세요."));
        }
    }


    //스터디 신청 인원 조회(postman test아직X , user 등록 후 다시 해보기)
    @GetMapping("recruits/{study_id}/members")
    public ResponseEntity<List<ApplySummaryDto>> findAllAppliers(@PathVariable Long study_id){
        try {
            List<ApplySummaryDto> appliedList=studyMemberService.findAllAppliers(study_id);
            return ResponseEntity.status(HttpStatus.OK).body(appliedList);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//            List<ApplySummaryDto> appliedList=studyMemberService.findAllAppliers(study_id);
//            return ResponseEntity.status(HttpStatus.OK).body(appliedList);
    }


    //스터디 멤버 승인
    @PatchMapping("recruits/{study_id}/members")
    public ResponseEntity<ResultDto<String>> approveMember(@PathVariable Long study_id){
        boolean isApproved = studyMemberService.approveMember(study_id);
        if (isApproved) {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디를 모집완료 상태로 변경합니다(모집중->모집완료)"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(), "스터디를 모집중 상태로 변경합니다(모집완료->모집중)"));
        }



}