package com.github.mall.common.exception;

import com.github.mall.common.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

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
    public Result defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.info("=====================全局异常信息捕获=======================");
        /*判断是否为没有权限异常*/
        if (ex instanceof AccessDeniedException) {
            log.error("没有操作权限:"+ex.getMessage(),ex);
            return Result.noRight(ex.getMessage());
        } else {
            log.error("系统服务异常:"+ex.getMessage(),ex);
            return Result.fail(ex.getMessage(), ex);
        }
    }
}
