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
import org.springframework.util.StringUtils;
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
public class AccessTokenFilter implements GlobalFilter, Ordered {

    /**
     * oauth
     */
    private final String OAUTH_TOKEN_URL = "service-auth";
    /**
     * 不需要验证权限的
     */
    private final String NO_RIGHT = "noRight";
    /**
     * access_token
     */
    private final String ACCESS_TOKEN = "access_token";
    /**
     * 获取有效token
     */
    private final String ACTIVE = "active";


    @Autowired
    private IAuthRpc iAuthRpc;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();

        String url = exchange.getRequest().getURI().toString();
        /*验证是否包含oauth,包含请求的全部放行*/
        if (isContainOauthUrl(url)) {
            return chain.filter(exchange);
        }

        /*获取请求的token*/
        ServerHttpRequest request = exchange.getRequest();
        String accessToken = request.getHeaders().getFirst(ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            log.info("access_token请求头为空");
            byte[] bits = setResponseBody(response, "1001", "access_token请求头为空", "", HttpStatus.UNAUTHORIZED);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            return response.writeWith(Mono.just(buffer));
        } else {
            /*包含token就去校验token是否有效*/
            Map<String, ?> checkToken = iAuthRpc.checkToken(accessToken);
            if (!Boolean.parseBoolean(String.valueOf(checkToken.get(ACTIVE)))) {
                /*token无效*/
                log.info("access_token无效");
                byte[] bits = setResponseBody(response, "1002", "access_token无效", "", HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                return response.writeWith(Mono.just(buffer));
            }

        }

        return chain.filter(exchange);
    }

    /**
     * 判断是否包含了验证服务URI
     *
     * @param url 请求路径
     * @return
     */
    private boolean isContainOauthUrl(String url) {
        url = url.split("\\?")[0];
        return url.contains(OAUTH_TOKEN_URL) || url.contains(NO_RIGHT);
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