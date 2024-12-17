package com.example.employeeshub.controllers;

import com.example.employeeshub.models.UserModel;
import org.springframework.web.bind.annotation.*;
import com.example.employeeshub.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //Inyectamos las dependencias, userController no depende directamente de userService.
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{email}")
    public Optional<UserModel> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
    @PostMapping
    public UserModel postUser(@RequestBody UserModel user) {
        return userService.postUser(user);
    }
    @PostMapping("/user/{user_id}/roles/{role_id}")

    @PutMapping("/{id}")
    public UserModel putUser(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.putUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
