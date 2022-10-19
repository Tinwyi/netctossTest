package com.qfedu.util;

import java.util.List;

public class PageResult {


    private List<?> list;  // 赋值任何泛型的集合都可

    private Integer currentPage; //当前页

    private Integer pageSize; // 一页显示的条数

    private Integer count;//总条数

    public PageResult() {
    }

    public PageResult(List<?> list, Integer currentPage, Integer pageSize, Integer count) {
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
