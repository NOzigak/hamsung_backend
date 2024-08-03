package hamsung.hamsung_project.domain.study.dto;


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
