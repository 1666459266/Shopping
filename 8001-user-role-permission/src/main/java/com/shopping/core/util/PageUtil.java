package com.shopping.core.util;

public class PageUtil {
    private Integer page;

    private Integer limit;

    public PageUtil() {
    }

    public PageUtil(Integer page, Integer limit) {
        if (page > 0)
            this.page = page;
        if (limit > 0)
            this.limit = limit;
    }

    public Integer pageNum(Integer page){
        return (page - 1) * limit;
    }

    public Integer limitNum(Integer limit){
        return limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
