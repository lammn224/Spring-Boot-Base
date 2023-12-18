package com.lammai.SpringBootBase.service;

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

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
