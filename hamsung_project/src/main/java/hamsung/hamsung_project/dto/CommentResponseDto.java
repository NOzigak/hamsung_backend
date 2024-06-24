package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.ChildComment;
import hamsung.hamsung_project.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private String username;

    private String text;

    private String modifiedDate;

    private List<ChildCommentResponseDto> childs;


    private CommentResponseDto(String username, String text, String modifiedDate, List<ChildCommentResponseDto> childs) {
        this.username = username;
        this.text = text;
        this.modifiedDate = modifiedDate;
        this.childs = childs;
    }


    public static CommentResponseDto of(Comment comment) {
        List<ChildCommentResponseDto> childs = new ArrayList<>();
        for (ChildComment c : comment.getChilds()) childs.add(ChildCommentResponseDto.of(c));

        return new CommentResponseDto(comment.getUser().getUsername(), comment.getText(), comment.getModifiedDate(), childs);
    }


}
