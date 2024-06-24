package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.ChildCommentRequestDto;
import hamsung.hamsung_project.dto.CommentRequestDto;
import hamsung.hamsung_project.entity.ChildComment;
import hamsung.hamsung_project.entity.Comment;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.repository.ChildCommentRepository;
import hamsung.hamsung_project.repository.CommentRepository;
import hamsung.hamsung_project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ChildCommentRepository childCommentRepository;
    private UserRepository userRepository;
//    private RecruitRepository recruitRepository;

    public CommentService(CommentRepository commentRepository, ChildCommentRepository childCommentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.childCommentRepository = childCommentRepository;
        this.userRepository = userRepository;
        //this.recruitRepository = recruitRepository;
    }

    // 댓글 생성
    public ResponseEntity createComment(Long recruitsId, Long userId , CommentRequestDto commentDto) {

        User user = userRepository.findById(userId).get();

        Comment comment = commentDto.toEntity();
        comment.setUser(user);
        commentRepository.save(comment);
        return ResponseEntity.ok("create comment successfully");
    }

    // 대댓글 생성
    public ResponseEntity createChildComment(Long parent_comment_id, ChildCommentRequestDto childCommentDto) {

        Comment comment = commentRepository.findById(parent_comment_id).get();
        Long userId = childCommentDto.getUserId();

        User user = userRepository.findById(userId).get();

        childCommentDto.setUserId(userId);
        childCommentDto.setUsername(user.getUsername());
        childCommentDto.setComment(comment);

        ChildComment childComment = childCommentDto.toEntity();
        childCommentRepository.save(childComment);
        return ResponseEntity.ok("create child comment successfully");
    }

    // 댓글 삭제
    public ResponseEntity deleteComment(Long commentId, Long userId) {
        Long commentUserId = commentRepository.findById(commentId).get().getUser().getId(); //
        if(!userId.equals(commentUserId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid UserId");
        }

        commentRepository.deleteById(commentId);
        return ResponseEntity.ok("delete comment successfully");
    }

    // 댓글 수정
    public ResponseEntity updateComment(Long commentId, Long userId, String text) {

        Comment comment = commentRepository.findById(commentId).get();
        Long commentUserId = comment.getUser().getId(); //
        // 수정자와 댓글 작성자가 다를 경우
        if(!userId.equals(commentUserId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid UserId");
        }

        comment.setText(text);
        // 수정 시간 변경, 생성 시간 -> 그대로
        comment.setModifiedDate(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));

        commentRepository.save(comment);
        return ResponseEntity.ok("update comment successfully");
    }


    // 대댓글 삭제
    public ResponseEntity deleteChildComment(Long commentId, Long userId) {

        Long commentUserId = childCommentRepository.findById(commentId).get().getUserId(); //
        if(!userId.equals(commentUserId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid UserId");
        }

        childCommentRepository.deleteById(commentId);
        return ResponseEntity.ok("delete comment successfully");
    }

    // 대댓글 수정
    public ResponseEntity updateChildComment(Long childCommentId, Long userId, String text) {

        ChildComment childComment = childCommentRepository.findById(childCommentId).get();
        Long commentUserId = childComment.getUserId(); //
        // 수정자와 댓글 작성자가 다를 경우
        if(!userId.equals(commentUserId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid UserId");
        }

        childComment.setText(text);
        // 수정 시간 변경, 생성 시간 -> 그대로
        childComment.setModifiedDate(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));

        childCommentRepository.save(childComment);
        return ResponseEntity.ok("update comment successfully");
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

}
