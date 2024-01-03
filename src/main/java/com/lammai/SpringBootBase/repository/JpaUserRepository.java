package com.lammai.SpringBootBase.repository;

import com.lammai.SpringBootBase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users ", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = ?1 AND u.id <> ?2")
    boolean existsByUsernameNotId(String username, Long id);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = ?1 AND u.id <> ?2")
    boolean existsByEmailNotId(String email, Long id);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = ?1")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);
}
