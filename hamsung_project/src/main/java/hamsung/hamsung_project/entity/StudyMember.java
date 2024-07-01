package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ColumnDefault("0")
    //false-승인대기중, true-승인완료
    //leader는 default가 true로.
    private boolean approval=false;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"user_id"})
//    @JsonManagedReference
    private User users;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

}
