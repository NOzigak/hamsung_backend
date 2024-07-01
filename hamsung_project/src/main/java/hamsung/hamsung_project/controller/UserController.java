package hamsung.hamsung_project.controller;

import hamsung.hamsung_project.dto.MyPageUserDto;
import hamsung.hamsung_project.dto.UserRequestDTO;
import hamsung.hamsung_project.entity.User;
import hamsung.hamsung_project.exception.InvalidDataException;
import hamsung.hamsung_project.service.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity createUser(@RequestBody UserRequestDTO userdto) {
        userService.joinUser(userdto);
        return ResponseEntity.ok("User registered successfully");
    }

    // 유저 한 명 조회
    @GetMapping("/users/{user_id}")
    public ResponseEntity searchUser(@PathVariable(name = "user_id") Long id) {

        User user = userService.findById(id);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity deleteUser(@PathVariable(name = "user_id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("delete success.");
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity updateUser(@PathVariable(name="user_id")Long id, UserRequestDTO userDTO) {

        userService.updateUser(id, userDTO);

        return new ResponseEntity<>("update success.", HttpStatus.OK);

//        if(!userService.updateUser(id, userDTO)) return ResponseEntity.status(401).body("invalid username");
//
//        return ResponseEntity.ok("update success.");
    }

    // 유저 한 명 조회
    @GetMapping("/myPage/{user_id}")
    public ResponseEntity myPageUser(@PathVariable(name = "user_id") Long id) {
        User user = userService.findById(id);
        MyPageUserDto myPageUserDto = new MyPageUserDto(user, user.getReview());

        return ResponseEntity.ok(myPageUserDto);
    }




}