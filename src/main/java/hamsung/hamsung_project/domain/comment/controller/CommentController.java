package hamsung.hamsung_project.domain.comment.controller;

import hamsung.hamsung_project.domain.comment.dto.ChildCommentRequestDto;
import hamsung.hamsung_project.domain.comment.dto.CommentRequestDto;
import hamsung.hamsung_project.domain.comment.dto.CommentResponseDto;
import hamsung.hamsung_project.domain.comment.dto.UpdateCommentRequest;
import hamsung.hamsung_project.domain.comment.entity.Comment;
import hamsung.hamsung_project.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //모집글에 댓글 생성
    @PostMapping("/recruits/{recruits-id}/comments")
    public ResponseEntity<String> createComment(@PathVariable(name = "recruits-id") Long recruitsId, Long userId, CommentRequestDto commentDto) {
        System.out.println(userId + commentDto.getText());
        commentService.createComment(recruitsId, userId, commentDto);
        return ResponseEntity.ok("create Comment successfully");
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name="comment-id") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("delete comment sucessfully");
    }

    // 댓글 수정
    @PutMapping("/comments/{comment-id}")
    public ResponseEntity<String> updateComment(@PathVariable(name="comment-id") Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        commentService.updateComment(commentId, updateCommentRequest);
        return ResponseEntity.ok("update comment successfully");
    }

    // 댓글 조회 (대댓글 까지 같이)
    @GetMapping("/comments/{comment-id}")
    public ResponseEntity<CommentResponseDto> findComment(@PathVariable(name="comment-id") Long commentId) {
        Comment comments = commentService.findByCommentId(commentId);
        return ResponseEntity.ok(CommentResponseDto.of(comments));
    }

    /*
    * 대댓글
    * */

    // 대댓글 생성
    @PostMapping("/child-comments/{parent-comment-id}")
    public ResponseEntity<String> createChildComment(@PathVariable(name="parent-comment-id") Long parentCommentId,@RequestBody
                                             ChildCommentRequestDto childCommentDto) {
        commentService.createChildComment(parentCommentId, childCommentDto);
        return ResponseEntity.ok("create child comment successfully");

    }

    // 대댓글 삭제
    @DeleteMapping("child-comments/{child-comment-id}")
    public ResponseEntity<String> deleteChildComment(@PathVariable(name="child-comment-id") Long chlidCommentId) {

        commentService.deleteChildComment(chlidCommentId);
        return ResponseEntity.ok("delete comment successfully");
    }

    // 대댓글 수정
    @PutMapping("/child-comments/{child-comment-id}")
    public ResponseEntity<String> updateChildComment(@PathVariable(name="child-comment-id") Long childCommentId,@RequestBody UpdateCommentRequest updateCommentRequest) {
        commentService.updateChildComment(childCommentId, updateCommentRequest);
        return ResponseEntity.ok("update comment successfully");

    }


    /*
    * 임시로 모든 댓글 응답을 JSON으로 보기 위해 만듦
    * */
    // 임시 모집글 관련 모든 댓글, 대댓글 불러오는 컨트롤러
    @GetMapping("/comments/recruits/{recruit_id}")
    public ResponseEntity<List<CommentResponseDto>> showAllComment(@PathVariable Long recruit_id){
        List<Comment> comments = commentService.findByCommentAllId(recruit_id);
        List<CommentResponseDto> commentsList = new ArrayList<>();
        for(Comment c: comments){
            commentsList.add(CommentResponseDto.of(c));
        }


        return ResponseEntity.ok(commentsList);
    }


}
