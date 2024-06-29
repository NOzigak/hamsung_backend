package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hamsung.hamsung_project.dto.ManageDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class Manage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int attendance;
    private int absent;
    private int late;
    private int homework;
    @Column(name="week_score")
    private Integer weekScore;
    private int week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    @JsonIgnore
    private Study study;

    public static Manage createManage(ManageDto dto, Study study){
        return Manage.builder().attendance(dto.getAttendance()).absent(dto.getAbsent()).late(dto.getLate()).homework(dto.getHomework()).weekScore(dto.getWeekScore()).week(dto.getWeek()).study(study)
                .build();
    }

}
