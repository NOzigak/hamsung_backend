package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private ReviewDto review;

}
