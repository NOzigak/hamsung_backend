package hamsung.hamsung_project.domain.comment.dto;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private Long userId;
    private String text;

}
