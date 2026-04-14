package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserRepository userRepository;

    // 🔥 GET CURRENT USER PROFILE
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {

        String email = authentication.getName(); // JWT se aata hai

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }

    // 🔥 UPDATE CURRENT USER PROFILE
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(Authentication authentication,
                                           @RequestBody User updatedUser) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 only allowed fields update
        user.setName(updatedUser.getName());

        return ResponseEntity.ok(userRepository.save(user));
    }
}
