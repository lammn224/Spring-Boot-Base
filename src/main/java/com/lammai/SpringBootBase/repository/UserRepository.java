package com.lammai.SpringBootBase.repository;

import com.lammai.SpringBootBase.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    @Autowired
    @Qualifier("postgresqlJdbcTemplate")
    private JdbcTemplate postgresqlJdbcTemplate;

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = postgresqlJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));

        return users;
    }
}
