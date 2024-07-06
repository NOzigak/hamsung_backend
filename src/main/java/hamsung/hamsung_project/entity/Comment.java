package hamsung.hamsung_project.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name ="recruit_id")
    private Recruit recruit; //스터디 모집글

    public static Comment createComment(Long id, String text, String createdDate, String modifiedDate) {
        return Comment.builder()
                .id(id).text(text).createdDate(createdDate).modifiedDate(modifiedDate).build();
    }

}