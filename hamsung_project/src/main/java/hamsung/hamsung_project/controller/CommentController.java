package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.ChildCommentRequestDto;
import hamsung.hamsung_project.dto.CommentRequestDto;
import hamsung.hamsung_project.dto.CommentResponseDto;
import hamsung.hamsung_project.entity.Comment;
import hamsung.hamsung_project.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //모집글에 댓글 생성
    @PostMapping("/recruits/{recruits-id}/comments")
    public ResponseEntity createComment(@PathVariable(name = "recruits-id") Long recruitsId, Long userId, CommentRequestDto commentDto) {
        return commentService.createComment(recruitsId, userId, commentDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable(name="comment-id") Long commentId, Long userId) {
        return commentService.deleteComment(commentId, userId);
    }

    // 댓글 수정
    @PutMapping("/comments/{comment-id}")
    public ResponseEntity updateComment(@PathVariable(name="comment-id") Long commentId, Long userId, String text) {
        return commentService.updateComment(commentId, userId, text);
    }

    // 댓글 조회 (대댓글 까지 같이)
    @GetMapping("/comments/{comment-id}")
    public ResponseEntity findComment(@PathVariable(name="comment-id") Long commentId) {
        Comment comments = commentService.findByCommentId(commentId);

        return ResponseEntity.ok(CommentResponseDto.of(comments));
    }

    /*
    * 대댓글
    * */

    // 대댓글 생성
    @PostMapping("/child-comments/{parent-comment-id}")
    public ResponseEntity createChildComment(@PathVariable(name="parent-comment-id") Long parentCommentId,
                                             Long userId, ChildCommentRequestDto childCommentDto) {

        return commentService.createChildComment(parentCommentId, childCommentDto);
    }

    // 대댓글 삭제
    @DeleteMapping("child-comments/{child-comment-id}")
    public ResponseEntity deleteChildComment(@PathVariable(name="child-comment-id") Long chlidCommentId, Long userId) {

        return commentService.deleteChildComment(chlidCommentId, userId);
    }

    // 대댓글 수정
    @PutMapping("/child-comments/{child-comment-id}")
    public ResponseEntity updateChildComment(@PathVariable(name="child-comment-id") Long childCommentId, Long userId, String text) {
        return commentService.updateChildComment(childCommentId, userId, text);
    }


}
