package com.lammai.SpringBootBase.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class BookController {

//    @Autowired
//    private BookRepository repository;

    @GetMapping("")
    public String findBooks() {
        return "List of books";
    }
}
