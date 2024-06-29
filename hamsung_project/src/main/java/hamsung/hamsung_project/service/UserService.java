package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.exception.InvalidDataException;
import hamsung.hamsung_project.repository.ReviewRepository;
import hamsung.hamsung_project.repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ReviewRepository reviewRepository;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.reviewRepository = reviewRepository;
    }

    public Long joinUser(UserRequestDTO userDTO){
        System.out.println("======Service======");

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        System.out.println("======= joinUser username 확인 ======");
        System.out.println(username);

        if (userRepository.existsByUsername(username)) {
            User tmp = userRepository.findByUsername(username);
            System.out.println(tmp.getUsername());
            throw new InvalidDataException("1 invalid username");
        }
        if (userRepository.existsByEmail(email))
            throw new IllegalStateException("2 invalid email");
        System.out.println(userDTO.getPassword());
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User data = userDTO.toEntity();

        userRepository.save(data);

        Review review=new Review();
        review.setNoLate(0L);
        review.setFaithful(0L);
        review.setKind(0L);
        review.setUnkind(0L);
        review.setFastAnswer(0L);
        review.setSlowAnswer(0L);
        review.setPassive(0L);
        review.setAbsent(0L);

        review.setUser(data);

//        reviewRepository.save(review);
//        data.setReview(review);

        userRepository.save(data);

        return data.getId();
    }

    public ResponseEntity updateUser(Long id, UserRequestDTO userDTO) {

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        String password= userDTO.getPassword();

        if (userRepository.existsByUsername(username))
            return new ResponseEntity<>("invalid username.", HttpStatus.BAD_REQUEST);
        if (userRepository.existsByEmail(email))
            return new ResponseEntity<>("invalid email.", HttpStatus.BAD_REQUEST);
        if (userRepository.existsById(id))
            return new ResponseEntity<>("not found user.", HttpStatus.BAD_REQUEST);


        User user = userRepository.findById(id).get();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
//        user.setImaged_num(userDTO.getImage_num());

        userRepository.save(user);
        return new ResponseEntity<>("update success.", HttpStatus.OK);
    }

    public Optional<User> findById(Long id) {
        Optional<User> data = userRepository.findById(id);
        return data;
    }

    public void deleteById(Long id) { userRepository.deleteById(id); }
}



