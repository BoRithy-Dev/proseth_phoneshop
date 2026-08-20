package com.app.proseth_phoneshop.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    int DEFAULT_PAGE_LIMIT = 10;   // default size = 2 items/page
    int DEFAULT_PAGE_NUMBER = 1;  // default page = 1
    String PAGE_LIMIT = "_limit"; // query param name
    String PAGE_NUMBER = "_page";// query param name

    static Pageable getPageable(int pageNumber, int pageSize){
        if(pageNumber < DEFAULT_PAGE_NUMBER){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if(pageSize <1){
            pageSize = DEFAULT_PAGE_LIMIT;
        }
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return pageable;
    }
}
