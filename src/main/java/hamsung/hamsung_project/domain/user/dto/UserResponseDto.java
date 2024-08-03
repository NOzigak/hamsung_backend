package hamsung.hamsung_project.domain.user.dto;

import hamsung.hamsung_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String username;

    private UserResponseDto(String username) {
        this.username = username;
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUsername());
    }

}
