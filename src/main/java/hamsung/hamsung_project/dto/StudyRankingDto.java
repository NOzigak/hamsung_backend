package hamsung.hamsung_project.dto;


import hamsung.hamsung_project.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StudyRankingDto {
    private Long id;
    private String title;
    private Integer score;


}
