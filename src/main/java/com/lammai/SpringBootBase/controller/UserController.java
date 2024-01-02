package com.lammai.SpringBootBase.controller;

import com.lammai.SpringBootBase.common.Pagination;
import com.lammai.SpringBootBase.dto.users.CreateUserDto;
import com.lammai.SpringBootBase.dto.users.UpdateUserDto;
import com.lammai.SpringBootBase.dto.users.UserResponseDto;
import com.lammai.SpringBootBase.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        UserResponseDto user = userService.createUser(createUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        UserResponseDto user = userService.updateUser(id, updateUserDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Pagination<UserResponseDto>> findAllUsersPaging(
            @RequestParam(name = "size", defaultValue = "4") @Min(4) Integer size,
            @RequestParam(name = "page", defaultValue = "1") @Min(1) Integer page,
            @RequestParam(name = "sorBy", required = false) String sortBy,
            @RequestParam(name = "sortType", defaultValue = "", required = false) String sortType
    ) {
        Pagination<UserResponseDto> result = userService.findAllUsersPaging(size, page, sortBy, sortType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
