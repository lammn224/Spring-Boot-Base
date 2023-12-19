package com.lammai.SpringBootBase.service;

import com.lammai.SpringBootBase.common.Pagination;
import com.lammai.SpringBootBase.dto.CreateUserDto;
import com.lammai.SpringBootBase.dto.UpdateUserDto;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User creatUser(CreateUserDto createUserDto) {
        return userRepository.creatUser(createUserDto);
    }

    public User updateUserById(Integer id, UpdateUserDto updateUserDto) {
        this.findUserById(id);

        return userRepository.updateUserById(id, updateUserDto);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public Pagination<User> findAllUsersPaging(int size, int page, String sortBy, String sortType) {
        int offset = page * size - size;
        return userRepository.findAllUsersPaging(size, offset, sortBy, sortType);
    }
}
