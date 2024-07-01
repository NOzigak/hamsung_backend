package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="no_late", nullable=false)
    private int noLate;

    @Column(name="faithful", nullable=false)
    private int faithful;

    @Column(name="kind", nullable=false)
    private int kind;

    @Column(name="unkind", nullable=false)
    private int unkind;

    @Column(name="fast_answer", nullable=false)
    private int fastAnswer;

    @Column(name="slow_answer", nullable=false)
    private int slowAnswer;

    @Column(name="passive", nullable=false)
    private int passive;

    @Column(name="absent", nullable=false)
    private int absent;

    @OneToOne
    @JoinColumn(name="user_id")
    @JsonBackReference //순환참조 방지(자식쪽)
    private User user;

    @Column(name="point")
    private int point;

}
