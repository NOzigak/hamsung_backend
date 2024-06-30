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
    private Long noLate;

    @Column(name="faithful", nullable=false)
    private Long faithful;

    @Column(name="kind", nullable=false)
    private Long kind;

    @Column(name="unkind", nullable=false)
    private Long unkind;

    @Column(name="fast_answer", nullable=false)
    private Long fastAnswer;

    @Column(name="slow_answer", nullable=false)
    private Long slowAnswer;

    @Column(name="passive", nullable=false)
    private Long passive;

    @Column(name="absent", nullable=false)
    private Long absent;

    @OneToOne
    @JoinColumn(name="user_id")
    @JsonBackReference //순환참조 방지(자식쪽)
    private User user;

    private int point;

}