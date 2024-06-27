package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.ChildComment;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChildCommentResponseDto {

    private Long parent_id;

    private Long id;

    private String username;

    private String text;

    private String modifiedDate;


    private ChildCommentResponseDto(Long parent_id, Long id,String username, String text, String modifiedDate) {
        this.parent_id = parent_id;
        this.id = id;
        this.username = username;
        this.text = text;
        this.modifiedDate = modifiedDate;
    }

    public static ChildCommentResponseDto of(ChildComment childComment) {
        return new ChildCommentResponseDto(childComment.getComment().getId(),childComment.getId(), childComment.getUsername(), childComment.getText(), childComment.getModifiedDate());
    }



}
