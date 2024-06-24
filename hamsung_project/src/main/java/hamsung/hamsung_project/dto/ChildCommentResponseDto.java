package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.ChildComment;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChildCommentResponseDto {

    private String username;

    private String text;

    private String modifiedDate;


    private ChildCommentResponseDto(String username, String text, String modifiedDate) {
        this.username = username;
        this.text = text;
        this.modifiedDate = modifiedDate;
    }

    public static ChildCommentResponseDto of(ChildComment childComment) {
        return new ChildCommentResponseDto(childComment.getUsername(), childComment.getText(), childComment.getModifiedDate());
    }



}
