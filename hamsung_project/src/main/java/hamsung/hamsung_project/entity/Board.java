package hamsung.hamsung_project.entity;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //객체 필드를 테이블의 컬럼으로 매핑.
    @Column(name="title")
    private String title;

    @Column(name="user_id")
    private BigInteger user_id;

    @Column(name="study_id")
    private BigInteger study_id;

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
    private BigInteger view;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;


    public long getRecruitsId() {
        return id;
    }

    public BigInteger getUserId() {
        return user_id;
    }

    public BigInteger getStudyId() {
        return study_id;
    }

    public Board(RecruitsRequestsDto requestsDto){
        this.id=requestsDto.getRecruitsId();
        this.user_id=requestsDto.getUserId();
        this.study_id=requestsDto.getStudyId();
        this.title=requestsDto.getTitle();
        this.description=requestsDto.getDescription();
        this.category=requestsDto.getCategory();
        this.capacity=requestsDto.getCapacity();
        this.place=requestsDto.getPlace();
        this.view=requestsDto.getView();
        this.startDate=requestsDto.getStartDate();
        this.endDate=requestsDto.getEndDate();
    }
}
