package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
public class RecruitsResponseDto {
    private Long id;
    @JsonProperty("user_id")
    private BigInteger userId;
    @JsonProperty("study_id")
    private BigInteger studyId;
    private String title;
    private String description;
    private String category;
    private BigInteger capacity;
    private String place;
    private Long view;
    @JsonProperty("start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate endDate;
    private BigInteger comments;
    @JsonProperty("createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createdAt;
    private Boolean isRecruit;



    //entity->dto
    public static RecruitsResponseDto createRecruitsDTO(Board board) {
        return new RecruitsResponseDto(null,null,null,board.getTitle(),board.getDescription(),board.getCategory()
                ,board.getCapacity(),board.getPlace(), board.getView(),board.getStartDate(),board.getEndDate(),null,board.getCreatedAt(),board.getIsRecruit());
    }




}