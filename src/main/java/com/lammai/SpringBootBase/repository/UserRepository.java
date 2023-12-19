package com.lammai.SpringBootBase.repository;

import com.lammai.SpringBootBase.common.Pagination;
import com.lammai.SpringBootBase.common.PaginationHelper;
import com.lammai.SpringBootBase.common.SqlBuilder;
import com.lammai.SpringBootBase.dto.CreateUserDto;
import com.lammai.SpringBootBase.dto.UpdateUserDto;
import com.lammai.SpringBootBase.exeption.BadRequestException;
import com.lammai.SpringBootBase.exeption.NotFoundException;
import com.lammai.SpringBootBase.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    @Autowired
    @Qualifier("postgresqlJdbcTemplate")
    private JdbcTemplate postgresqlJdbcTemplate;

    public User findUserById(Integer id) {
        try {
            String sql = SqlBuilder.getSqlQueryById("users", "find-user-by-id");
            return postgresqlJdbcTemplate.queryForObject(
                    sql,
                    BeanPropertyRowMapper.newInstance(User.class),
                    id
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new NotFoundException("USER_NOT_EXIST");
        }
    }

    public User findUserByEmail(String email) {
        try {
            String sql = SqlBuilder.getSqlQueryById("users", "find-user-by-email");
            return postgresqlJdbcTemplate.queryForObject(
                    sql,
                    BeanPropertyRowMapper.newInstance(User.class),
                    email
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new NotFoundException("USER_NOT_EXIST");
        }
    }

    public User creatUser(CreateUserDto createUserDto) {
        String sql = SqlBuilder.getSqlQueryById("users","create-user");
        User insertedUser;
        int rowInserted;

        try {
            rowInserted = postgresqlJdbcTemplate.update(
                    sql,
                    createUserDto.getEmail()
            );

            if(rowInserted > 0) {
                insertedUser = this.findUserByEmail(createUserDto.getEmail());
            } else {
                throw new BadRequestException("CREATED_FAIL", "Create user failed");
            }
        } catch (DataAccessException e) {
            throw new BadRequestException("BAD_REQUEST", e.toString());
        }

        return insertedUser;
    }

    public User updateUserById(Integer id, UpdateUserDto updateUserDto) {
        String sql = SqlBuilder.getSqlQueryById("users","update-user-by-id");
        User updatedUser;

        int rowUpdated = postgresqlJdbcTemplate.update(
                sql,
                updateUserDto.getEmail(),
                id
        );

        if(rowUpdated > 0) {
            updatedUser = this.findUserById(id);
        } else {
            throw new NotFoundException("USER_NOT_EXIST");
        }

        return updatedUser;
    }

    public List<User> findAllUsers() {
        String sql = SqlBuilder.getSqlQueryById("users","find-all-users");
        List<User> users = postgresqlJdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(User.class)
        );

        return users;
    }

    public Pagination<User> findAllUsersPaging(int size, int offset, String sortBy, String sortType) {
        String sql = SqlBuilder.getSqlQueryById("users","find-all-users");

        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("email")
        );

        PaginationHelper<User> paginationHelper = new PaginationHelper<>(postgresqlJdbcTemplate);

        return paginationHelper.getPage(sql, size, offset, sortBy, sortType, rowMapper);
    }
}
