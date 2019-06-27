package com.github.mall.order.vo;

import lombok.Data;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-27
 * @description： 支付响应实体
 */
@Data
public class AliPayResponseVo {

    /*网页支付响应体*/
    private String webPayBody;

    /*app支付响应体*/
    private String appPayBody;


}
