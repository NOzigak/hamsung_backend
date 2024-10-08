package hamsung.hamsung_project.domain.post.controller;

import hamsung.hamsung_project.domain.post.dto.PostDto;
import hamsung.hamsung_project.global.config.auth.CustomUserDetails;
import hamsung.hamsung_project.global.util.ResultDto;
import hamsung.hamsung_project.domain.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/study/{studyId}/posts")
    public ResponseEntity<PostDto> createPost(@PathVariable Long studyId, @RequestBody PostDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        PostDto createdDto=postService.createPost(studyId,dto,customUserDetails.getUsername());
        
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId, @RequestBody PostDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        String username=customUserDetails.getUsername();

        Long userId=customUserDetails.getId();
        PostDto postDto=postService.update(postId, dto, username);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> showOnePost(@PathVariable Long postId){
        PostDto target=postService.showOnePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ResultDto<String>> deletePost(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        String username=customUserDetails.getUsername();
        boolean isDeleted=postService.deletePost(postId, username);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"공지사항/일정이 삭제되었습니다."));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(), "공지사항/일정 삭제에 실패하였습니다."));
    }

    @GetMapping("/study/{studyId}/posts")
    public ResponseEntity<?> showStudyAnnounce(@PathVariable Long studyId, @RequestParam String type){
        if("announcement".equals(type)){
            List<PostDto> announceList=postService.showStudyPost(studyId, type);
            if(announceList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultDto.res(HttpStatus.NOT_FOUND.toString(), "해당 스터디에 등록된 공지사항이 없습니다."));
            }
            return ResponseEntity.status(HttpStatus.OK).body(announceList);
        }
        else if("schedule".equals(type)){
            List<PostDto> scheduleList=postService.showStudyPost(studyId, type);
            if(scheduleList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultDto.res(HttpStatus.NOT_FOUND.toString(), "해당 스터디에 등록된 일정이 없습니다."));
            }
            return ResponseEntity.status(HttpStatus.OK).body(scheduleList);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(),"Invalid type parameter"));
    }

    @GetMapping("/study/{studyId}/{date}")
    public ResponseEntity<?> showDatePost(@PathVariable Long studyId, @PathVariable LocalDate date){
        List<PostDto> DatePostList=postService.showDatePost(studyId, date);
        if(DatePostList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"해당 날짜로 기록된 일정이 없습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DatePostList);
    }

    @GetMapping("/study/{studyId}/allPosts")
    public ResponseEntity<?> showStudyPost(@PathVariable Long studyId){
        List<PostDto> postList=postService.showAllPosts(studyId);
        if(postList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"해당 스터디에 등록된 공지사항 및 일정이 없습니다."));

        }
        return ResponseEntity.status(HttpStatus.OK).body(postList);

    }

}
