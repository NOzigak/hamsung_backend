package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudyDto {
    private Long id;
    private String category;
    private String place;
    private Integer member_num;
    private Boolean status;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    private int score;
    private Long leader_id;
    private String title;




    //entity->dto
    public static StudyDto createStudyDto(Study study){
        return new StudyDto(study.getId(),study.getCategory(),study.getPlace(),study.getMember_num(),
                study.getStatus(),study.getStartDate(),study.getEndDate(),study.getScore(),study.getLeader_id(),study.getTitle());
    }


}
