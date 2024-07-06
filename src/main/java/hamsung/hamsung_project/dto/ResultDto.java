package hamsung.hamsung_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResultDto<T> {
    private String status;
    private T message;

    public static <T> ResultDto<T> res(final String status, final T t){
        return ResultDto.<T>builder()
                .status(status)
                .message(t)
                .build();
    }
}
