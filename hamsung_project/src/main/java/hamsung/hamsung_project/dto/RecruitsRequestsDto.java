package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class RecruitsRequestsDto {

//    private Long id;
    @JsonProperty("user_id")
    private Long user_id;
    private String title;
    private String description;
    private String category;
    private String place;
    private Integer capacity;
    private Boolean isRecruit;
    private Integer view;



}


