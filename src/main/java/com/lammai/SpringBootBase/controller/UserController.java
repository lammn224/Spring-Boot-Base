package com.lammai.SpringBootBase.controller;

import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
