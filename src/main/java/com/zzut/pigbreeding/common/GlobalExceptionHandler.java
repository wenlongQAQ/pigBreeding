package com.zzut.pigbreeding.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

// 拦截加有 xx 注解的
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody //拦截异常后返回R对象转换为json数据
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String[] s = ex.getMessage().split(" ");
            String msg ="不允许有相同的设备名称->"+ s[2] + "已存在!";
            return msg;
        }

        else return "啊,未知错误?";
    }

}
