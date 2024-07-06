package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;

    private String text;

    private String createdDate =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private String modifiedDate=
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private UserResponseDto userResponseDto;

    public Comment toEntity() {
        return Comment.createComment(id, text, createdDate, modifiedDate);
    }



//    private Recruit recruit; //스터디 모집글

//    /*Dto -> Entity*/
//    public Comment comment(){
//        Comment comment = Comment.builder()
//                .id(id)
//                .text(text)
//                .createdDate(createdDate)
//                .modifiedDate(modifiedDate)
//                .build();
//
//        return comment;
//    }


}
