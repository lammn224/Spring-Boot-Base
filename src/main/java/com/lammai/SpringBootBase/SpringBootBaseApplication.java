package com.lammai.SpringBootBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			CreateUserDto createAdmin = new CreateUserDto("ngoclam.work@gmail.com", "12345678", "lammn2", Role.ADMIN);
//			CreateUserDto createUser = new CreateUserDto("hoanglam2242001@gmail.com", "12345678", "ngoclam", Role.USER);
//
//			userService.createUser(createAdmin);
//			userService.createUser(createUser);
//		};
//	}
}
