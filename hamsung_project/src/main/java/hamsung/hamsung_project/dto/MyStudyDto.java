package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.Study;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyStudyDto {
    private Long id;
    private String category;
    private String place;
    private Integer member_num;
    private Boolean status;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    private Integer score;
    private Long leader_id;
    @JsonProperty("my_role")
    private String myRole;
    @JsonProperty("study_name")
    private String studyName;

    public static MyStudyDto createMyStudyDto(Study study, Long userId, Board board){
        String myRole = study.getLeader_id().equals(userId) ? "leader" : "member";
        String studyName=board.getTitle();

        return new MyStudyDto(study.getId(),study.getCategory(),study.getPlace(),study.getMember_num(),
                study.getStatus(),study.getStartDate(),study.getEndDate(),study.getScore(),study.getLeader_id(), myRole, studyName);
    }
}
