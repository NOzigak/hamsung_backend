package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@Entity //테이블 생성
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Recruit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //객체 필드를 테이블의 컬럼으로 매핑.
    @Column(name="title",nullable=false)
    private String title;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User users;


    @JoinColumn(name="study_id")
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    private Study study;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private String category;

    @Column(name="place")
    private String place;

    @Column(name="capacity")
    private Integer capacity;

    @Column(name="isRecruit")
    private Boolean isRecruit;

    @Builder.Default()
    @Column(name="view")
    @ColumnDefault("0")
    private Integer view=0;

//
//    @Column
//    @Formula("(SELECT COUNT(*) FROM comment c WHERE c.recruit_id = id)")
//    private Integer commentCount;


    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDate createdAt;


    @PrePersist
    protected void onCreate() {
        if (view == null) {
            view = 0;
        }
        createdAt = LocalDate.now();
    }

    public static Recruit createRecruit(RecruitsRequestsDto requestDto, User user) {
        Recruit recruit = new Recruit();
        recruit.setTitle(requestDto.getTitle());
        recruit.setUsers(user);
        recruit.setDescription(requestDto.getDescription());
        recruit.setCategory(requestDto.getCategory());
        recruit.setPlace(requestDto.getPlace());
        recruit.setCapacity(requestDto.getCapacity());
        recruit.setIsRecruit(requestDto.getIsRecruit());
        recruit.setView(requestDto.getView());
        // createdAt 등 필요한 설정 추가 가능
        return recruit;
    }

    public static Recruit updateRecruit(RecruitsRequestsDto dto,Recruit target){
        target.setTitle(dto.getTitle());
        target.setDescription(dto.getDescription());
        target.setCategory(dto.getCategory());
        target.setPlace(dto.getPlace());
        target.setCapacity(dto.getCapacity());
        target.setIsRecruit(dto.getIsRecruit());
        target.setView(dto.getView());
        return target;
    }




}