package hamsung.hamsung_project.entity;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity //테이블 생성
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //객체 필드를 테이블의 컬럼으로 매핑.
    @Column(name="title",nullable=false)
    private String title;

    @Column(name="user_id")
    private Long userId;

    @Column(name="study_id")
    private Long studyId;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private String category;

    @Column(name="place")
    private String place;

    @Column(name="capacity")
    private BigInteger capacity;

    @Column(name="isRecruit")
    private Boolean isRecruit;

    @Column(name="view")
    private long view;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="comments")
    private BigInteger comments;

    @Column(name="created_at")
    private LocalDate createdAt;


    //dto->엔티티
    public static Board createRecruit(RecruitsRequestsDto requestDto){
        //user_id,study_id는 매핑으로
        return new Board(null, requestDto.getTitle(), null,null,requestDto.getDescription(),
                requestDto.getCategory(), requestDto.getPlace(),requestDto.getCapacity(),
                requestDto.getIsRecruit(),requestDto.getView(),requestDto.getStartDate(),requestDto.getEndDate(),null,null);
    }


}