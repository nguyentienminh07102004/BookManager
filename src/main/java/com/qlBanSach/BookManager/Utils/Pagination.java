package com.qlBanSach.BookManager.Utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class Pagination {
    public Pageable pageUtil(Integer page, Integer limit, Integer pageDefault, Integer limitDefault) {
        if(page == null || page < 1) {
            page = pageDefault;
        }
        if(limit == null || limit < 1) {
            limit = limitDefault;
        }
        return PageRequest.of(page - 1, limit);
    }

    public Pageable pageUtil(Integer page, Integer limit) {
        if(page == null || page < 1) {
            page = 1;
        }
        if(limit == null || limit < 1) {
            limit = 3;
        }
        Pageable pageable = PageRequest.of(page - 1, limit);
        return pageable;
    }
}
