package com.lammai.SpringBootBase.service;

import com.lammai.SpringBootBase.common.Pagination;
import com.lammai.SpringBootBase.common.PaginationHelper;
import com.lammai.SpringBootBase.dto.users.CreateUserDto;
import com.lammai.SpringBootBase.dto.users.UpdateUserDto;
import com.lammai.SpringBootBase.dto.users.UserMapper;
import com.lammai.SpringBootBase.dto.users.UserResponseDto;
import com.lammai.SpringBootBase.exeption.BadRequestException;
import com.lammai.SpringBootBase.exeption.NotFoundException;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JpaUserRepository jpaUserRepository;
    private final PaginationHelper paginationHelper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(CreateUserDto createUserDto) {
        boolean existingUsername = this.jpaUserRepository.existsByUsername(createUserDto.getUsername());
        boolean existingEmail = this.jpaUserRepository.existsByEmail(createUserDto.getEmail());

        if(existingUsername){
            throw new BadRequestException(USERNAME_ALREADY_EXIST);
        } else if (existingEmail) {
            throw new BadRequestException(EMAIL_ALREADY_EXIST);
        }

        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        User newUser = jpaUserRepository.save(UserMapper.INSTANCE.createUser(createUserDto));

        return UserMapper.INSTANCE.userResponse(newUser);
    }

    public UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User existingUser = this.jpaUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));

        boolean existingUsername = this.jpaUserRepository.existsByUsernameNotId(updateUserDto.getUsername(), id);
        boolean existingEmail = this.jpaUserRepository.existsByEmailNotId(updateUserDto.getEmail(), id);

        if(existingUsername){
            throw new BadRequestException(USERNAME_ALREADY_EXIST);
        } else if (existingEmail) {
            throw new BadRequestException(EMAIL_ALREADY_EXIST);
        }

        UserMapper.INSTANCE.updateUser(updateUserDto, existingUser);
        User updatedUser = jpaUserRepository.save(existingUser);

        return UserMapper.INSTANCE.userResponse(updatedUser);
    }

    public UserResponseDto findById(Long id) {
        User user = jpaUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return UserMapper.INSTANCE.userResponse(user);
    }

    public User findByUsername(String username) {
        User user = jpaUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));

        return user;
    }

    public List<UserResponseDto> findAllUsers() {
        List<User> users = jpaUserRepository.findAllUsers();

        List<UserResponseDto> res = users.stream()
                .map(UserMapper.INSTANCE::userResponse)
                .collect(Collectors.toList());

        return res;
    }

    public Pagination<UserResponseDto> findAllUsersPaging(int size, int page, String sortBy, String sortType) {
        Pagination<User> userPagination = paginationHelper.getPage(jpaUserRepository, size, page, sortBy, sortType);

        List<UserResponseDto> userResponseDtoList = userPagination.getData().stream()
                .map(UserMapper.INSTANCE::userResponse)
                .collect(Collectors.toList());

        return new Pagination<>(userResponseDtoList, userPagination.getTotalElements());
    }
}
