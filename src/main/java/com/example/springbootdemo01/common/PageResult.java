package com.example.springbootdemo01.common;

import java.util.List;

/**
 *
 * @author: 留住风的小树
 * @desc: 分页结果类
 */
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
