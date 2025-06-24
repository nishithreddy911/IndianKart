package com.nishi.user.controller;

import com.nishi.user.dto.APIResponse;
import com.nishi.user.dto.UserDTO;
import com.nishi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse<UserDTO>> getUser(@PathVariable(name = "id") long id) {
        UserDTO userFromDB = userService.getUser(id);
        return ResponseEntity.ok(new APIResponse<>(true, "User fetched successfully", userFromDB, LocalDateTime.now()));
    }

    @PostMapping("/add-user")
    public ResponseEntity<APIResponse<UserDTO>> addUser(@RequestBody UserDTO user) {
        UserDTO newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(true, "User created successfully", newUser, LocalDateTime.now()));
    }

    @PutMapping("/update-user")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(@RequestBody UserDTO user) {
        UserDTO updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(
                new APIResponse<>(true, "Updated user details successfully", updatedUser, LocalDateTime.now()));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable(name = "id") Long userId) {
        String message = userService.deleteUser(userId);
        return ResponseEntity.ok(new APIResponse<>(true, message, null, LocalDateTime.now()));
    }
}
