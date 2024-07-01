package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.ReviewDto;
import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Boolean createReview(Long userId, ReviewDto reviewDto) {
        /* userId로 해당하는 review찾기 -> reviewDto에서 get한 애들값을했을 떄 true면 review 필드 +1해주기 -> reviewRepository에 갱신된 review 저장(save)*/
        Review review=reviewRepository.findByUser_Id(userId).orElseThrow(()->new IllegalArgumentException("없는 유저입니다"));

        Map<String, Consumer<Review>> updateActions=new HashMap<>();
        updateActions.put("noLate",r->r.setNoLate(r.getNoLate()+1));
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
