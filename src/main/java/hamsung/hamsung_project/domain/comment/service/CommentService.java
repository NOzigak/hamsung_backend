package hamsung.hamsung_project.domain.comment.service;

import hamsung.hamsung_project.domain.comment.dto.ChildCommentRequestDto;
import hamsung.hamsung_project.domain.comment.dto.CommentRequestDto;
import hamsung.hamsung_project.domain.comment.dto.UpdateCommentRequest;
import hamsung.hamsung_project.domain.comment.entity.ChildComment;
import hamsung.hamsung_project.domain.comment.entity.Comment;
import hamsung.hamsung_project.domain.recruit.entity.Recruit;
import hamsung.hamsung_project.domain.user.entity.User;
import hamsung.hamsung_project.global.exception.InvalidDataException;
import hamsung.hamsung_project.domain.comment.repository.ChildCommentRepository;
import hamsung.hamsung_project.domain.comment.repository.CommentRepository;
import hamsung.hamsung_project.domain.recruit.repository.RecruitsRepository;
import hamsung.hamsung_project.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ChildCommentRepository childCommentRepository;
    private final UserRepository userRepository;
    private final RecruitsRepository recruitsRepository;
//    private RecruitRepository recruitRepository;

    public CommentService(CommentRepository commentRepository, ChildCommentRepository childCommentRepository, UserRepository userRepository, RecruitsRepository recruitsRepository ) {
        this.commentRepository = commentRepository;
        this.childCommentRepository = childCommentRepository;
        this.userRepository = userRepository;
        this.recruitsRepository = recruitsRepository;
    }

    // 댓글 생성
    public Long createComment(Long recruitsId, Long userId , CommentRequestDto commentDto) {
        // 유저 조회 실패시
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidDataException("Invalid UserId"));

        Recruit recruit = recruitsRepository.findById(recruitsId).get();

        // 텍스트가 비어있으면
        if(commentDto.getText().equals("")) {
            throw new InvalidDataException("Invalid Text");
        }

        Comment comment = commentDto.toEntity();
        comment.setUser(user);
        comment.setRecruit(recruit); // 아직 미완
        commentRepository.save(comment);
        return comment.getId();
    }

    // 대댓글 생성
    public Long createChildComment(Long parent_comment_id, ChildCommentRequestDto childCommentDto) {

        // 부모 댓글이 존재 하지 않는 경우 -> 예외
        Comment comment = commentRepository.findById(parent_comment_id).
                orElseThrow(() -> new InvalidDataException("Invalid Parent CommentId"));

        // 유저가 존재하지 않는 경우 예외
        Long userId = childCommentDto.getUserId();
        User user = userRepository.findById(userId).
                orElseThrow(() -> new InvalidDataException("Invalid UserId"));

        // childComment 객체 초기화
        childCommentDto.setUserId(userId);
        childCommentDto.setUsername(user.getUsername());
        childCommentDto.setComment(comment);

        ChildComment childComment = childCommentDto.toEntity();
        childCommentRepository.save(childComment);
        return childComment.getId();
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        // 댓글이 존재하지 않는 경우 -> 에외
        commentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidDataException("Invalid commentId"));

        commentRepository.deleteById(commentId);
    }

    // 댓글 수정
    public Long updateComment(Long commentId, UpdateCommentRequest updateCommentRequest) {

        // CommentId로 조회가 안되는 경우 예외
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidDataException("Invalid CommentId"));

        // 수정자와 댓글 작성자가 다를 경우 예외
        if(!updateCommentRequest.getUserId().equals(comment.getUser().getId()))
            throw new InvalidDataException("Invalid UserId");

        // Text가 비어있는 경우
        String text = updateCommentRequest.getText();
        if (text.equals(""))
            throw new InvalidDataException("Invalid Text");


        comment.setText(text);
        // 수정 시간 변경, 생성 시간 -> 그대로
        comment.setModifiedDate(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));

        commentRepository.save(comment);
        return comment.getId();
    }


    // 대댓글 삭제
    public void deleteChildComment(Long commentId) {

        // 대댓글 작성자 Id 추출하기 -> 대댓글 조회 안되면 예외
        childCommentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidDataException("Invalid ChildCommentId"));

        childCommentRepository.deleteById(commentId);
    }

    // 대댓글 수정
    public Long updateChildComment(Long childCommentId, UpdateCommentRequest updateCommentRequest) {
        ChildComment childComment = childCommentRepository.findById(childCommentId)
                .orElseThrow(() -> new InvalidDataException("Invalid ChildCommentId"));

        Long commentUserId = childComment.getUserId(); //
        // 수정자와 댓글 작성자가 다를 경우
        if(!updateCommentRequest.getUserId().equals(commentUserId)){
            throw new InvalidDataException("Invalid UserId");
        }
        String text = updateCommentRequest.getText();
        if (text.equals("")) {
            throw new InvalidDataException("Invalid text");
        }

        childComment.setText(text);
        // 수정 시간 변경, 생성 시간 -> 그대로
        childComment.setModifiedDate(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));

        childCommentRepository.save(childComment);
        return childComment.getId();
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidDataException("Invalid CommentId"));
    }

    /*
     * 임시로 모든 댓글 응답을 JSON으로 보기 위해 만듦
     * */
    // 임시로 만든 모집글의 모든 comment 조회하는 서비스 로직
    public List<Comment> findByCommentAllId(Long recruit_id){
        if(!recruitsRepository.findById(recruit_id).isPresent())
            throw new InvalidDataException("Invalid RecruitId");

        return commentRepository.findAllByRecruitId(recruit_id);
    }

}