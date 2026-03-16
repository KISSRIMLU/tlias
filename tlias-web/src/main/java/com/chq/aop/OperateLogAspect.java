package com.chq.aop;

import com.chq.mapper.OperateLogMapper;
import com.chq.pojo.OperateLog;
import com.chq.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.chq.annotation.LogOperation;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Ricardo
 * @since 2026/3/16 20:06
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    // 注入日志业务层（负责控制台输出+数据库入库）
    @Autowired
    private OperateLogMapper operateLogMapper;


    @Before("@annotation(logOperation)")
    public void before(JoinPoint joinPoint,LogOperation logOperation) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("记录日志：类名：{}，方法名：{}，参数：{}", className, methodName, Arrays.toString(args));
    }

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, LogOperation log)throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        // 需要实现 getCurrentUserId 方法
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);

        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }

    // 示例方法，获取当前用户ID
    private int getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}