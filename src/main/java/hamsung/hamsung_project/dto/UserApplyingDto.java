package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApplyingDto {
    @JsonProperty("user_id")
    private Long userId;
    private String username;
}
