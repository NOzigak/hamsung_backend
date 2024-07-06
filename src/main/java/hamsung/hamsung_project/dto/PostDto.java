package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Post;
import hamsung.hamsung_project.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String type;
    private String description;
    private String title;
    @JsonProperty("due_date")
    private LocalDate dueDate;
    @JsonProperty("study_id")
    private Long studyId;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static PostDto createPostDto(Post post) {
        return new PostDto(post.getId(), post.getType(), post.getDescription(), post.getTitle(), post.getDueDate(), post.getStudy().getId(), post.getCreated_at(), post.getUpdated_at());
    }

    public Post toEntity(Study study){
        return new Post(id, type, description, title, dueDate, created_at, updated_at, study);
    }
}
