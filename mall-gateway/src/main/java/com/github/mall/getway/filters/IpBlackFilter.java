package com.github.mall.getway.filters;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ：HPC
 * @date ：Created in 2019-07-24
 * @description： 黑名单过滤器
 */
@Slf4j
@Component
public class IpBlackFilter extends AbstractGlobalFilter {


    @Autowired
    private RedissonClient client;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        /*获取黑名单集合*/
        /**
         * ip黑名单key
         */
        String ipBlackKey = "mall:ipBlack";
        RSet<String> ips = client.getSet(ipBlackKey);
        /*获取请求ip*/
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
        for (String str : ips) {
            /*存在于黑名单里直接返回*/
            if (str.contains(ip)) {
                byte[] bits = setResponseBody(response, "1005", "你以被禁止访问", null);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                return response.writeWith(Mono.just(buffer));
            }
        }

        /*继续执行后面的过滤器*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
