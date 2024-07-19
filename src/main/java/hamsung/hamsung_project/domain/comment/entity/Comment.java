package hamsung.hamsung_project.domain.comment.entity;

import hamsung.hamsung_project.domain.user.entity.User;
import hamsung.hamsung_project.domain.recruit.entity.Recruit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String createdDate;

    private String modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id") // 작성자 id
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChildComment> childs = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name ="recruit_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Recruit recruit; //스터디 모집글

    public static Comment createComment(Long id, String text, String createdDate, String modifiedDate) {
        return Comment.builder()
                .id(id).text(text).createdDate(createdDate).modifiedDate(modifiedDate).build();
    }

}