package hamsung.hamsung_project.domain.recruit.entity;

import hamsung.hamsung_project.domain.study.entity.Study;
import hamsung.hamsung_project.domain.user.entity.User;
import hamsung.hamsung_project.domain.recruit.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User users;


    @JoinColumn(name="study_id")
//    @JsonBackReference
    @OneToOne(fetch=FetchType.LAZY)
    private Study study;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private String category;

    @Column(name="place")
    private String place;

    @Column(name="capacity")
    private Integer capacity;

    //추가
    @Builder.Default()
    @Column(name="isRecruit")
    private Boolean isRecruit=false;

    @Builder.Default()
    @Column(name="view")
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
        recruit.setIsRecruit(false);
        recruit.setView(0);
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