package com.github.mall.getway.filters;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author ：HPC
 * @date ：Created in 2019-07-24
 * @description： 重复提交拦截器 设置同一个请求连接只能3秒请求一次
 */
@Component
@Slf4j
public class ReCommitFilter extends AbstractGlobalFilter {


    @Autowired
    private RedissonClient client;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        /*key*/
        String recommit = "mall:recommit";
        /*获取有效请求连接url*/
        String url = request.getPath().pathWithinApplication().value();
        /*获取ip*/
        String hostAddress = request.getRemoteAddress().getAddress().getHostAddress();
        /*key*/
        String key = recommit + ":" + url + ":" + hostAddress;
        /*从redis中获取值，如果不为空就说明是同一个请求*/
        RMapCache<String, String> map = client.getMapCache(key);
        /*判断是否为空，为空就可以放行请求*/
        String value = map.get("key");
        if (value != null) {
            byte[] bits = setResponseBody(response, "1006", "请求过于频繁", null);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            return response.writeWith(Mono.just(buffer));
        }
        /*保存至redis*/
        map.put("key", "", 3, TimeUnit.SECONDS);
        Mono<Void> filter = chain.filter(exchange);
        map.remove("key");
        return filter;

    }

    @Override
    public int getOrder() {
        return -9;
    }
}
