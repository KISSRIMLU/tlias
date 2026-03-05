package com.chq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/5 22:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private Long total;
    //总记录数
    private List<T> rows;
    //当前页数据列表
}