package com.chq.exception;

/**
 * 自定义业务异常（专门处理如班级有学生无法删除这类业务规则异常）
 * @author Ricardo
 */

public class BusinessException extends RuntimeException {
    // 仅需一个接收错误信息的构造方法
    public BusinessException(String message) {
        super(message);
        // 把提示语传给父类
    }
}