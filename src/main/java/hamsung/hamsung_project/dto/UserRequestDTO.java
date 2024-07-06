package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private Long id;

    private String username;

    private String email;

    private String password;

    private int image_num;

    public User toEntity(){
        return User.createUser(id, username, email, password, image_num, "ROLE_USER", "BRONZE", 0);
    }

}
