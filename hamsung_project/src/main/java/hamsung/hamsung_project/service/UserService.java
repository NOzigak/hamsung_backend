package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Long joinUser(UserRequestDTO userDTO){

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();

        if (userRepository.existsByUsername(username))
            throw new IllegalStateException("invalid username");
        if (userRepository.existsByEmail(email))
            throw new IllegalStateException("invalid email");

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User data = userDTO.toEntity();

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
        user.setImaged_num(userDTO.getImage_num());

        userRepository.save(user);
        return new ResponseEntity<>("update success.", HttpStatus.OK);
    }

    public Optional<User> findById(Long id) {
        Optional<User> data = userRepository.findById(id);
        return data;
    }

    public void deleteById(Long id) { userRepository.deleteById(id); }
}



