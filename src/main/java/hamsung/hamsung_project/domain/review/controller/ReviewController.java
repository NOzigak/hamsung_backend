package hamsung.hamsung_project.domain.review.controller;

import hamsung.hamsung_project.global.util.ResultDto;
import hamsung.hamsung_project.domain.review.dto.ReviewDto;
import hamsung.hamsung_project.domain.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review/{userId}")
    public ResponseEntity<ResultDto<String>> createReview( @PathVariable Long userId, @RequestBody ReviewDto dto) {
        Boolean result=reviewService.createReview(userId, dto);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.res(HttpStatus.OK.toString(),"리뷰등록 성공!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.res(HttpStatus.BAD_REQUEST.toString(),"리뷰등록 실패!"));
    }

}
