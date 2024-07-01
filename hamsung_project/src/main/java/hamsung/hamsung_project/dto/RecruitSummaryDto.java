package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Recruit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RecruitSummaryDto {
    private Long id;
    private String title;
    private String category;
    private String username; //nickname
    private String place;
    private Integer capacity;
    private Boolean isRecruit;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDate createdAt;

    public static RecruitSummaryDto createRequestDto(Recruit recruit){
        return new RecruitSummaryDto(recruit.getId(), recruit.getTitle(),
                recruit.getCategory(), recruit.getUsers().getUsername(), recruit.getPlace(), recruit.getCapacity(),
                recruit.getIsRecruit(), recruit.getCreatedAt());
    }


}