package com.lammai.SpringBootBase.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {
    private List<T> data;
    private Long totalElements;
}

