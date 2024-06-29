package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.service.StudyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StudyMemberController {
    private final StudyMemberService studyMemberService;
}
