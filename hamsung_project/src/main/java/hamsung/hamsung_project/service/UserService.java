package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.exception.InvalidDataException;
import hamsung.hamsung_project.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public Optional<User> findById(Long id) {
        if(!userRepository.findById(id).isPresent())
            throw new InvalidDataException("invalid user");

        Optional<User> data = userRepository.findById(id);
        return data;
    }

    public void deleteById(Long id) { userRepository.deleteById(id); }
}



