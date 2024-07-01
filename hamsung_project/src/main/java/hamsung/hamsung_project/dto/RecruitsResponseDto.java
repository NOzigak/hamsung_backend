package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.entity.User;
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
    private Long user_id;
    @JsonProperty("study_id")
    private Long study_id;
    private String title;
    private String description;
    private String category;
    private Integer capacity;
    private String place;
    private Integer view;
    private BigInteger comments;
    @JsonProperty("createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createdAt;
//    @JsonProperty("study_id")
//    private Study study;




    //entity->dto
    public static RecruitsResponseDto createRecruitsDTO(Board board) {
        return new RecruitsResponseDto(board.getId(),board.getUsers().getId(),board.getStudy().getId(), board.getTitle(),board.getDescription(),board.getCategory()
                ,board.getCapacity(),board.getPlace(), board.getView(),board.getComments(),board.getCreatedAt());
    }








}