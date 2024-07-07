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
    private Integer score;
    private Long leader_id;


//    public StudyDto(String category, String place, LocalDate startDate, LocalDate endDate) {
//        this.category = category;
//        this.place = place;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
//
//    public static StudyDto createStudyFromRequest(RecruitsRequestsDto requestsDto){
//        String category= requestsDto.getCategory();
//        String place= requestsDto.getPlace();
//        LocalDate startDate=requestsDto.getStartDate();
//        LocalDate endDate=requestsDto.getEndDate();
//
//        return new StudyDto(category,place,startDate,endDate);
//    }
//
//    public static hamsung.hamsung_project.recruits.dto.StudyDto toStudyDto(Study study) {
//        return new StudyDto(study.getId(), study.getCategory(), study.getPlace(), study.getMember_num(), study.getStatus(),
//                study.getStartDate(), study.getEndDate(), study.getScore(), study.getLeader_id());
//    }


    //entity->dto
    public static StudyDto createStudyDto(Study study){
        return new StudyDto(study.getId(),study.getCategory(),study.getPlace(),study.getMember_num(),
                study.getStatus(),study.getStartDate(),study.getEndDate(),study.getScore(),study.getLeader_id());
    }


}
