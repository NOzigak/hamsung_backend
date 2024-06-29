package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManageDto {
    @JsonProperty("study_id")
    private Long studyId;

    private int attendance;
    private int absent;
    private int late;
    private int homework;
    private int week;
    @JsonProperty("week_score")
    private Integer weekScore;


}
