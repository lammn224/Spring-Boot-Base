package com.lammai.SpringBootBase.config;

import com.lammai.SpringBootBase.exeption.NotFoundException;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.USER_NOT_EXIST;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final JpaUserRepository jpaUserRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> jpaUserRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
