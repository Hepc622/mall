package com.github.mall.getway.filters;

import com.alibaba.fastjson.JSONObject;
import com.github.mall.auth.rpc.IAuthRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author : HPC
 * @description : token权限拦截器
 * @date : 2019/7/4 11:17
 */
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private IAuthRpc iAuthRpc;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst("token");
        ServerHttpResponse response = exchange.getResponse();
        if (token == null || token.isEmpty()) {
            log.info("Token 为空请去验证");
            byte[] bits = setResponseBody(response, "1001", "token为空", "", HttpStatus.UNAUTHORIZED);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            return response.writeWith(Mono.just(buffer));
        } else {
            // TODO: 2019/7/4 校验token
            Map<String, ?> map = iAuthRpc.checkToken(token);

            if (!true) {
                byte[] bits = setResponseBody(response, "1001", "无效token", "", HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * @author : HPC
     * @description : 设置响应体
     * @date : 2019/7/4 14:48
     */
    private byte[] setResponseBody(ServerHttpResponse response, String code, String msg, Object data, HttpStatus statusCode) {
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("msg", msg);
        message.put("data", data);

        response.setStatusCode(statusCode);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return message.toJSONString().getBytes(StandardCharsets.UTF_8);
    }
}