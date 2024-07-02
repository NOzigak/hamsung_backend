package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Post;
import hamsung.hamsung_project.entity.Recruit;
import hamsung.hamsung_project.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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


