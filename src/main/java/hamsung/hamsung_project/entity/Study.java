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
    private Integer score;

    @Column(name="leader_id",nullable = false)
    private Long leader_id;



    //dto->entity
    public static Study createStudyEntity(RecruitsRequestsDto requestsDto) {
        return new Study(
                null,
                requestsDto.getCategory(),
                requestsDto.getPlace(),
                0, // members_num 기본값
                null, // status 기본값
                null, // startDate 기본값
                null, //endDate 기본값
                null, //score 기본값
                requestsDto.getUser_id() //leader_id
        );
    }

    public static Study updateStudy(RecruitsRequestsDto dto,Study study){
        study.setCategory(dto.getCategory());
        study.setPlace(dto.getPlace());

        return study;
    }


}
