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
    private boolean approval;

    public boolean getApproval(){
        return approval;
    }
}
