package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(UserRequestDTO userdto) {

        userService.joinUser(userdto);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity searchUser(@PathVariable(name = "user_id") Long id) {

        Optional<User> userOptional = userService.findById(id);
        try {
            User user = userOptional.get();
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("not found user");
        }
    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity deleteUser(@PathVariable(name = "user_id") Long id) {

        userService.deleteById(id);
        return ResponseEntity.ok("delete success.");
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity updateUser(@PathVariable(name="user_id")Long id, UserRequestDTO userDTO) {

        return userService.updateUser(id, userDTO);

//        if(!userService.updateUser(id, userDTO)) return ResponseEntity.status(401).body("invalid username");
//
//        return ResponseEntity.ok("update success.");
    }





}