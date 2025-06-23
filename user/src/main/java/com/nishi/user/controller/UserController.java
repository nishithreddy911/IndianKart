package com.nishi.user.controller;

import com.nishi.user.dto.APIResponse;
import com.nishi.user.dto.UserDTO;
import com.nishi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return null;
    }

    @PostMapping("/add-user")
    public ResponseEntity<APIResponse<UserDTO>> addUser(@RequestBody UserDTO user) {
        UserDTO newUser = userService.createUser(user);
    }

    @PutMapping("/update-user")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(@RequestBody UserDTO user) {
        return null;
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable(name = "id") Long userId) {
        return null;
    }
}
