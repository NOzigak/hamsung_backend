package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /*
     * 임시로 모든 댓글 응답을 JSON으로 보기 위해 만듦
     * */
    // 임시 모집글 관련 모든 댓글, 대댓글 불러오는 컨트롤러
     List<Comment> findAllByRecruitId(Long recruit_id);

}
