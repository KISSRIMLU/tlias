package com.chq.mapper;

import com.chq.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ricardo
 * @since 2026/3/6 18:42
 */

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);
}
