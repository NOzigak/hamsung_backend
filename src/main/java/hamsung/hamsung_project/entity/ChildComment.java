package hamsung.hamsung_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChildComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="child_comment_id")
    private Long id;

    private String text;

    private String createdDate;

    private String modifiedDate;

    private Long userId;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;


}
