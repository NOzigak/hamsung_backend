package hamsung.hamsung_project.domain.review.service;


import hamsung.hamsung_project.domain.review.dto.ReviewDto;
import hamsung.hamsung_project.domain.review.entity.Review;
import hamsung.hamsung_project.domain.review.repository.ReviewRepository;
import hamsung.hamsung_project.global.exception.InvalidDataException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Boolean createReview(Long userId, ReviewDto reviewDto) {
        /* userId로 해당하는 review찾기 -> reviewDto에서 get한 애들값을했을 떄 true면 review 필드 +1해주기 -> reviewRepository에 갱신된 review 저장(save)*/
        Review review=reviewRepository.findByUser_Id(userId).orElseThrow(()->new InvalidDataException("없는 유저입니다"));

        Map<String, Consumer<Review>> updateActions=new HashMap<>();  //리뷰 객체 Review를 받아서 그 객체의 특정 필드를 수정하는 람다 함수를 매핑
        updateActions.put("noLate",r->r.setNoLate(r.getNoLate()+1));   //r:Review 객체
        updateActions.put("faithful", r -> r.setFaithful(r.getFaithful() + 1));
        updateActions.put("kind", r -> r.setKind(r.getKind() + 1));
        updateActions.put("unkind", r -> r.setUnkind(r.getUnkind() + 1));
        updateActions.put("fastAnswer", r -> r.setFastAnswer(r.getFastAnswer() + 1));
        updateActions.put("slowAnswer", r -> r.setSlowAnswer(r.getSlowAnswer() + 1));
        updateActions.put("passive", r -> r.setPassive(r.getPassive() + 1));
        updateActions.put("absent", r -> r.setAbsent(r.getAbsent() + 1));

        reviewDto.toMap().forEach((key,value)->{
            if(Boolean.TRUE.equals(value)){
                updateActions.get(key).accept(review);
            }
        });

        int positive=review.getNoLate()+review.getFaithful()+review.getKind()+review.getFastAnswer();
        int negative=review.getUnkind()+review.getSlowAnswer()+review.getPassive()+review.getAbsent();
        int score=positive-negative;

        review.setPoint(score);
        reviewRepository.save(review);
        return true;
    }
}
