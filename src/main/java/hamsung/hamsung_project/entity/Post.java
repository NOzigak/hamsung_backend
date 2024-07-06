package hamsung.hamsung_project.entity;

import hamsung.hamsung_project.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type", nullable=false)
    private String type;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="title")
    private String title;

    @Column(name="due_date")
    private LocalDate dueDate;

    @CreationTimestamp
    @Column(updatable=false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    public Post(Long id, String type, String description, String title, LocalDate dueDate, Study target) {
        this.id=id;
        this.type=type;
        this.description=description;
        this.title=title;
        this.dueDate=dueDate;
        this.study=target;
    }


    public static Post createPost(Study target, PostDto dto){
        if(dto.getId()!=null)
            throw new IllegalArgumentException("공지사항/일정관리 생성 실패! 공지사항/일정관리의 id가 없어야 합니다");
        if(dto.getStudyId()!=target.getId())
            throw new IllegalArgumentException("공지사항/일정관리 생성 실패! 스터디의 id가 잘못되었습니다");
        if("announcement".equals(dto.getType()))
            return new Post(null, dto.getType(), dto.getDescription(), dto.getTitle(), null, target);
        else if("schedule".equals(dto.getType()))
            return new Post(null, dto.getType(), dto.getDescription(), null, dto.getDueDate(), target);

        throw new IllegalArgumentException("공지사항/일정관리 생성 실패! 형식이 잘못되었습니다");
    }

}
