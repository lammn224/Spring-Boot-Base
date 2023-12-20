package com.lammai.SpringBootBase.common;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaginationHelper {
    public <T> Pagination<T> getPage(JpaRepository<T, Long> jpaRepository, int size, int page, String sortBy, String sortType) {
        Pageable pageable = createPageable(size, page, sortBy, sortType);
        Page<T> entityPage = jpaRepository.findAll(pageable);

        List<T> entities = entityPage.getContent();
        long totalElements = entityPage.getTotalElements();

        return new Pagination<>(entities, totalElements);
    }

    private Pageable createPageable(int size, int page, String sortBy, String sortType) {
        Pageable pageable;

        if (sortBy != null) {
            if (sortType != null && sortType.equalsIgnoreCase("desc")) {
                pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());
            } else {
                pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).ascending());
            }
        } else {
            pageable = PageRequest.of(page - 1, size);
        }

        return pageable;
    }
}

