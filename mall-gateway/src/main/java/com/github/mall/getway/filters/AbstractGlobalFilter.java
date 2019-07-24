package com.github.mall.getway.filters;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * @author ：HPC
 * @date ：Created in 2019-07-24
 * @description： 基础filter
 */
public abstract class AbstractGlobalFilter implements GlobalFilter, Ordered {
    /**
     * @author : HPC
     * @description : 设置响应体
     * @date : 2019/7/4 14:48
     */
    byte[] setResponseBody(ServerHttpResponse response, String code, String msg, Object data) {
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("msg", msg);
        message.put("data", data);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return message.toJSONString().getBytes(StandardCharsets.UTF_8);
    }
}
