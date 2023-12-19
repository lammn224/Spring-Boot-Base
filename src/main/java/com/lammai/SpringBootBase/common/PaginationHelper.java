package com.lammai.SpringBootBase.common;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


@AllArgsConstructor
public class PaginationHelper<T> {

    private final JdbcTemplate jdbcTemplate;

    public Pagination<T> getPage(String sql, int size, int offset, String sortBy, String sortType, RowMapper<T> rowMapper) {
        Long totalElements = countRows(sql);
        String orderBySql;

        if(sortBy != null) {
            if(sortType != null && sortType.equalsIgnoreCase("desc")) {
                orderBySql = " ORDER BY " + sortBy + " DESC ";
            } else {
                orderBySql = " ORDER BY " + sortBy + " ASC ";
            }

            sql = sql + orderBySql;
        }

        sql = sql + " LIMIT ? OFFSET ? ";

        List<T> data = jdbcTemplate.query(
                sql,
                rowMapper,
                size, offset
        );

        return new Pagination<>(data, totalElements);
    }

    private Long countRows(String sql) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM (" + sql + ") AS totalElements",
                Long.class
        );
    }
}

