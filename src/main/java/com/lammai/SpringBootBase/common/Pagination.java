package com.lammai.SpringBootBase.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Pagination<T> {
    private final List<T> data;
    private final Long totalElements;
}

