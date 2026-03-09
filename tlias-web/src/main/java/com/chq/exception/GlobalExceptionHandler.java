package com.chq.exception;

import com.chq.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ricardo
 */
@Slf4j
//声明控制器全局异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 专门处理自定义的业务异常（优先级更高）
    @ExceptionHandler(BusinessException.class) // 只捕获BusinessException
    public Result handleBusinessException(BusinessException e) {
        log.error("业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
        // 返回自定义的提示语
    }

    //声明异常处理方法
    @ExceptionHandler
    public Result ex(Exception e) {//方法形参中指定能够处理的异常类型
        e.printStackTrace();//打印堆栈中的异常信息
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }


}