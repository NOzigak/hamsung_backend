package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ApplyingSummaryDto {
    private Long user_id;
    private String username;
    private Review review;


    //    // summaryMember 메서드
    //    public static List<ApplyingSummaryDto> summaryMembers(StudyMemberDto dto) {
    //        return List<ApplyingSummaryDto>(dto.getUser().getId(), dto.getUser().getUsername(), dto.getReview());
    //    }
    // summaryMembers 메서드
    public static List<ApplyingSummaryDto> summaryMembers(List<StudyMemberDto> studyMemberDto) {
        List<ApplyingSummaryDto> summaryDtos = new ArrayList<>();
        for (StudyMemberDto dto : studyMemberDto) {
            summaryDtos.add(new ApplyingSummaryDto(dto.getUser().getId(), dto.getUser().getUsername(), dto.getReview()));
        }
        return summaryDtos;
    }
}
