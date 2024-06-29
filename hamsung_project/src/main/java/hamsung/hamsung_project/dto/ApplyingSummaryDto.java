package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class ApplySummaryDto {
    private Long user_id;
    private String username;
    private Review review;

    public  ApplySummaryDto(StudyMemberDto dto){

    }
