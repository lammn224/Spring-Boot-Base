package com.lammai.SpringBootBase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonProperty("id")
    private long id;

    @JsonProperty("email")
    private String email;
}
