package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name="comments")
    private BigInteger comments;

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


//    //dto->엔티티
//    public static Board createRecruit(RecruitsRequestsDto requestDto,User user){
//        //comments,createdAt 해결해야.
//        return new Board(null, requestDto.getTitle(),user,null,requestDto.getDescription(),
//                requestDto.getCategory(), requestDto.getPlace(),requestDto.getCapacity(),
//                requestDto.getIsRecruit(),requestDto.getView(),null,null);
//    }

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

    public static Recruit createRecruitWithStudy(Recruit recruit, Study study){
        return new Recruit(recruit.getId(), recruit.getTitle(), recruit.getUsers(),study, recruit.getDescription(),
                recruit.getCategory(), recruit.getPlace(), recruit.getCapacity(), recruit.getIsRecruit(), recruit.getView()
                , recruit.getComments(), recruit.getCreatedAt());
    }





}