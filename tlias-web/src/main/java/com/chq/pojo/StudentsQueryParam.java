package com.chq.pojo;

import lombok.Data;

/**
 * @author Ricardo
 * @since 2026/3/9 20:44
 */
@Data
public class StudentsQueryParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;
    private Integer pageSize = 10;
}
