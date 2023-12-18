package com.lammai.SpringBootBase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("id")
    private long id;

    @JsonProperty("email")
    private String email;
}
