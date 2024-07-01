package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {

    private Long noLate;

    private Long faithful;

    private Long kind;

    private Long unkind;

    private Long fastAnswer;

    private Long slowAnswer;

    private Long passive;

    private Long absent;

    private int point;

    public static ReviewResponseDto of(Review review){
        return ReviewResponseDto.builder()
                .noLate(review.getNoLate())
                .faithful(review.getFaithful())
                .kind(review.getKind())
                .unkind(review.getUnkind())
                .fastAnswer(review.getFastAnswer())
                .slowAnswer(review.getSlowAnswer())
                .passive(review.getSlowAnswer())
                .absent(review.getAbsent())
                .point(review.getPoint())
                .build();
    }



}
