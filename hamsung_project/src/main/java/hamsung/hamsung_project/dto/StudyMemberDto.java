package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudyMemberDto {
    private Study study;
    private User user;
    private Review review;
    private String role;
    private Boolean approval;

    public StudyMemberDto(Long user_id, String username, Review review) {
        this.user = new User(); // 새로운 User 객체를 생성
        this.user.setId(user_id);
        this.user.setUsername(username);
        this.review = review;

    }

}
