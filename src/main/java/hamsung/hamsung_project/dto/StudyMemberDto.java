package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.entity.StudyMember;
import hamsung.hamsung_project.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudyMemberDto {
    private Study study;
    private User users;
//    private Review review;
    private String role;
    private Boolean approval;



//    // 생성자
//    public StudyMemberDto(Long user_id, String username, Review review) {
//        this.users = new User(); // 새로운 User 객체를 생성
//        this.users.setId(user_id);
//        this.users.setUsername(username);
//        this.review = review;
//
//    }

    public static StudyMemberDto createMemberDto(StudyMember studyMember){
       return new StudyMemberDto(studyMember.getStudy(),studyMember.getUsers(),studyMember.getRole(),studyMember.getApproval());
    }
}
