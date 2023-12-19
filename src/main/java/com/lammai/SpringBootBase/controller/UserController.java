package com.lammai.SpringBootBase.controller;

import com.lammai.SpringBootBase.common.Pagination;
import com.lammai.SpringBootBase.dto.CreateUserDto;
import com.lammai.SpringBootBase.dto.UpdateUserDto;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> creatUser(@RequestBody CreateUserDto createUserDto) {
        User user = userService.creatUser(createUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Integer id, @RequestBody UpdateUserDto updateUserDto) {
        User user = userService.updateUserById(id, updateUserDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Pagination<User>> findAllUsersPaging(
            @RequestParam(name = "size", defaultValue = "10") @Min(10) Integer size,
            @RequestParam(name = "page", defaultValue = "1") @Min(1) Integer page,
            @RequestParam(name = "sorBy", required = false) String sortBy,
            @RequestParam(name = "sortType", defaultValue = "", required = false) String sortType
    ) {
        Pagination<User> result = userService.findAllUsersPaging(size, page, sortBy, sortType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
