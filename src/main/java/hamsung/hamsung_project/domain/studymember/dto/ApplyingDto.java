package hamsung.hamsung_project.domain.studymember.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.domain.review.entity.Review;
import hamsung.hamsung_project.domain.user.dto.UserApplyingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ApplyingDto {
    @JsonProperty("study_id")
    private Long studyId;
    private UserApplyingDto user;
    private Review review;//통째로 가져오기

}
