package com.github.mall.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 全局异常处理
 *
 * @author HPC
 * @create 2019-03-03
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Map defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.info("=====================全局异常信息捕获=======================");
        log.info(ex.getMessage(), ex);
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("code", "1000");
        map.put("msg", "系统繁忙");
        map.put("data", ex.getMessage());
        return map;
    }
}
