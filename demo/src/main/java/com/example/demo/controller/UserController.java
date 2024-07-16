package com.example.demo.controller;

import com.example.demo.dto.RegisterUserDTO;
import com.example.demo.entity.User;
import com.example.demo.jwtservices.AuthenticationService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

//@RequestMapping("/users")
//@RestController
//public class UserController {
//
//    @Autowired
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<User> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        User currentUser = (User) authentication.getPrincipal();
//
//        return ResponseEntity.ok(currentUser);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<List<User>> allUsers() {
//        List <User> users = userService.allUsers();
//
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping
//    @PreAuthorize("hasRole('MANAGER')") // Only managers can access this endpoint
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.allUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasRole('MANAGER')") // Only managers can create users
//    public ResponseEntity<User> createUser(@RequestBody RegisterUserDTO registerUserDTO) {
//        User user = userService.createUser(registerUserDTO, "STAFF");
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//
//    // Endpoint for managers to create new staff accounts
//    @PreAuthorize("hasRole('MANAGER')")
//    @PostMapping("/createUser")
//    public ResponseEntity<User> createUser(@RequestBody RegisterUserDTO registerUserDTO, @RequestParam String role) {
//        if (!"STAFF".equals(role)) {
//            return ResponseEntity.badRequest().body(null); // Managers can only create staff accounts
//        }
//        User user = userService.createUser(registerUserDTO, role);
//        return ResponseEntity.ok(user);
//    }
//}
@RestController
@RequestMapping("/manager")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody RegisterUserDTO registerUserDTO, @RequestParam String role) {
        if (!"STAFF".equals(role)) {
            return ResponseEntity.badRequest().body(null); // Managers can only create staff accounts
        }
        User user = authenticationService.signup(registerUserDTO, role);
//        User user = userService.createUser(registerUserDTO, role);

        System.out.println("Hello");

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'STAFF')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
