package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardSummaryDto {
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

    public static BoardSummaryDto createRequestDto(Board board){
        return new BoardSummaryDto(board.getId(),board.getTitle(),
                board.getCategory(),board.getUsers().getUsername(),board.getPlace(),board.getCapacity(),
                board.getIsRecruit(),board.getCreatedAt());
    }


}