package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {

    private int noLate;

    private int faithful;

    private int kind;

    private int unkind;

    private int fastAnswer;

    private int slowAnswer;

    private int passive;

    private int absent;

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
