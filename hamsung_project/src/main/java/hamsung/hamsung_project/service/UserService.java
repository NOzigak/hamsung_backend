package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.repository.ReviewRepository;
import hamsung.hamsung_project.repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import hamsung.hamsung_project.exception.InvalidDataException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ReviewRepository reviewRepository;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.reviewRepository = reviewRepository;
    }

    public Long joinUser(UserRequestDTO userDTO){

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();

        if (userRepository.existsByUsername(username))
            throw new InvalidDataException("validate username");
        if (userRepository.existsByEmail(email))
            throw new InvalidDataException("validate email");

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User data = userDTO.toEntity();

        userRepository.save(data);

        Review review=new Review();
        review.setNoLate(0);
        review.setFaithful(0);
        review.setKind(0);
        review.setUnkind(0);
        review.setFastAnswer(0);
        review.setSlowAnswer(0);
        review.setPassive(0);
        review.setAbsent(0);

        review.setUser(data); //연관 관계의 주인이 세팅해주어야 db에 외래키가 null값이 아닌 원하는 값이 저장됨

        reviewRepository.save(review);
        data.setReview(review);

        userRepository.save(data);

        return data.getId();
    }

    public Long updateUser(Long id, UserRequestDTO userDTO) {

        String username = userDTO.getUsername();
//        String email = userDTO.getEmail();
//        String password= userDTO.getPassword();

        if (userRepository.existsByUsername(username))
            throw new InvalidDataException("invalid username.");
//        if (userRepository.existsByEmail(email))
//            throw new InvalidDataException("invalid email.");
        // 유저 id 검증
        if (userRepository.existsById(id))
            throw new InvalidDataException("not found user.");
        User user = userRepository.findById(id).get();

        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setImaged_num(userDTO.getImage_num());

        userRepository.save(user);
        return user.getId();
    }

    public User findById(Long id) {
        if(!userRepository.findById(id).isPresent())
            throw new InvalidDataException("invalid user");

        User data = userRepository.findById(id).get();
        return data;
    }

    public void deleteById(Long id) { userRepository.deleteById(id); }
}



