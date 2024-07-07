package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.User;

import lombok.Getter;

@Getter
public class MyPageUserDto {
    private String username;

    private String email;

    private float point;

    private int imaged_num;

    private String badge;

    private ReviewResponseDto reviewResponseDto;

    public MyPageUserDto(User user, Review review) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.imaged_num = user.getImaged_num();
        this.badge = user.getBadge();
        this.reviewResponseDto = ReviewResponseDto.of(review);
    }

}
