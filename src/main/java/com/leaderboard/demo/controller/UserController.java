package com.leaderboard.demo.controller;

import com.leaderboard.demo.entity.User;
import com.leaderboard.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")

public class UserController {
     private final UserService userService;

     public UserController(UserService userService){
         this.userService = userService;
     }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
         return ResponseEntity.ok(userService.getAllUsers());
     }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
         return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser){
          return ResponseEntity.ok(userService.updateUser(id, updatedUser));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
         userService.deleteUser(id);
         return ResponseEntity.ok("User deleted successfully");
    }
}
