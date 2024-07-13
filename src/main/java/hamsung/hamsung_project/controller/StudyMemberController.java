package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.ApplyingSummaryDto;
import hamsung.hamsung_project.dto.MemberSummaryDto;
import hamsung.hamsung_project.exception.InvalidDataException;
import hamsung.hamsung_project.repository.StudyRepository;
import hamsung.hamsung_project.service.StudyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudyMemberController {
    @Autowired
    private final StudyMemberService studyMemberService;

    //스터디 멤버 조회
    @GetMapping("/studymember/{study_id}")
    public ResponseEntity<List<MemberSummaryDto>> findAllMembers(@PathVariable Long study_id) {
        try {
            List<MemberSummaryDto> memberList = studyMemberService.findAllMembers(study_id);
            return ResponseEntity.status(HttpStatus.OK).body(memberList);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//            List<ApplySummaryDto> appliedList=studyMemberService.findAllAppliers(study_id);
//            return ResponseEntity.status(HttpStatus.OK).body(appliedList);
    }


}
