package hamsung.hamsung_project.entity;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category",nullable=false)
    private String category;

    @Column(name="place")
    private String place;

    @Column(name = "member_num")
    @ColumnDefault("0")
    @Builder.Default()
    private Integer member_num=0;

    //스터디 진행중, 진행완료
    //모집완료( 시부터 진행중
    @Column(name="status")
    @ColumnDefault("false")
    @Builder.Default()
    private Boolean status=false;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name="score")
//    @ColumnDefault("0")
//    @Builder.Default()
    private Integer score;

    @Column(name="leader_id",nullable = false)
    private Long leader_id;


//    // 모든 필드를 포함하는 생성자 //필요한가?
//    public Study(Long id, String category, String place, Long member_num, Boolean status, LocalDate startDate, LocalDate endDate, Long score, Long leader_id) {
//        this.id = id;
//        this.category = category;
//        this.place = place;
//        this.member_num = member_num != null ? member_num : 0L;
//        this.status = status != null ? status : false;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.score = score != null ? score : 0L;
//        this.leader_id = leader_id;
//    }


    //dto->entity
    public static Study createStudyEntity(RecruitsRequestsDto requestsDto) {
        return new Study(
                requestsDto.getId(),
                requestsDto.getCategory(),
                requestsDto.getPlace(),
                0, // 기본값
                false, // 기본값
                requestsDto.getStartDate(),
                requestsDto.getEndDate(),
                null, // 기본값
                requestsDto.getUserId() //leader_id
        );
    }

}
