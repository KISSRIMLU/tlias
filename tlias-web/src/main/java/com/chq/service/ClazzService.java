package com.chq.service;

import com.chq.pojo.Clazz;
import com.chq.pojo.ClazzsQueryParam;
import com.chq.pojo.PageResult;



/**
 * @author Ricardo
 * @since 2026/3/8 15:14
 */
public interface ClazzService {
    PageResult<Clazz> findClazz(ClazzsQueryParam clazzQueryParam);
}
