package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.ChildComment;
import hamsung.hamsung_project.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildCommentRequestDto {

    private Long id;

    private String text;

    private String createdDate =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private String modifiedDate=
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private Long userId;

    private String username;

    private Comment comment;

    public ChildComment toEntity(){
        return new ChildComment(id, text, createdDate, modifiedDate, userId,username, comment);
    }


}
