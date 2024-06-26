package hamsung.hamsung_project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private int imaged_num;

    private String role;

    private String badge;

    private float point;

    @OneToOne(mappedBy="user")
    @JsonManagedReference //순환참조 방지 (부모쪽)
    private Review review;


}
