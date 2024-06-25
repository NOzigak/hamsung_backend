package hamsung.hamsung_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name ="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public static User createUser(Long id, String username, String email, String password, int imaged_num, String role, String badge, float point) {
        return User.builder().id(id).username(username).email(email).password(password)
                .imaged_num(imaged_num).role(role).badge(badge).point(point).build();
    }



}
