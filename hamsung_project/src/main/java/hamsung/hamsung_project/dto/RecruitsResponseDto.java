package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Comment;
import hamsung.hamsung_project.entity.Recruit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
public class RecruitsResponseDto {
    private Long id;
    @JsonProperty("user_id")
    private Long user_id;
    @JsonProperty("study_id")
    private Long study_id;
    private String title;
    private String description;
    private String category;
    private Integer capacity;
    private String place;
    private Integer view;
//    private Integer commentCount;
    @JsonProperty("createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createdAt;
//    @JsonProperty("study_id")
//    private Study study;




    //entity->dto
    public static RecruitsResponseDto createRecruitsDTO(Recruit recruit) {
        return new RecruitsResponseDto(recruit.getId(), recruit.getUsers().getId(), recruit.getStudy().getId(), recruit.getTitle(), recruit.getDescription(), recruit.getCategory()
                , recruit.getCapacity(), recruit.getPlace(), recruit.getView(), recruit.getCreatedAt());
    }








}