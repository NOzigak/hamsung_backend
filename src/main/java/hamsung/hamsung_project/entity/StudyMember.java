package hamsung.hamsung_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Table(name="studymember")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="role") //leader,member
    @ColumnDefault("'member'")
    private String role="member";

    @Column(name="approval")
    //false-승인대기중, true-승인완료
    //leader는 default가 true로.
    private Boolean approval=false;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;



}

