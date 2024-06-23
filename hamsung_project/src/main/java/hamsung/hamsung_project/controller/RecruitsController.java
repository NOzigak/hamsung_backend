package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.dto.RecruitsResponseDto;
import hamsung.hamsung_project.service.RecruitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitsController {
    private final RecruitsService recruitsService;

//    //게시글 전체 조회 -> 수정해야함.
//    @GetMapping("/recruits")
//    public List<Recruit> getPosts(){
//        return recruitsService.getPosts();
//    }

    //게시글 작성
    @PostMapping("/recruits")
    public RecruitsResponseDto createPost(@RequestBody RecruitsRequestsDto requestsDto){
        return RecruitsService.createPost(requestsDto);
    }

    //    //게시물 전체 조회
//    @GetMapping("/recruits")
//    public List<RecruitsResponseDto> getPosts(){
//        return RecruitsService.getPosts();
//    }
//
//    //스터디 모집글 수정
//    @PutMapping("/recruits/{id}")
//    public RecruitsResponseDto updatePost(@PathVariable Long id,@RequestBody RecruitsRequestsDto requestsDto) {
//        return RecruitsService.updatePost(id,requestsDto);
//    }
//
//    //스터디 모집글 상세 조회
//    @GetMapping("/recruits/{id}")
//    public getAPost(){
//        return RecruitsService.getAPost();
//    }

    //스터디 모집상태 변경

    //모집글 조회수 증가

    //스터디 지원하기

    //스터디 신청 인원 조회

    //스터디 멤버 조회

    //스터디 멤버 승인


}
