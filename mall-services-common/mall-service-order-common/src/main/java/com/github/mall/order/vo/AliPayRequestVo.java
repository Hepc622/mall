package com.github.mall.order.vo;

import lombok.Data;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-25
 * @description：支付请求参数实体
 */
@Data
public class AliPayRequestVo {

    /*商户订单号，商户网站订单系统中唯一订单号*/
    private String out_trade_no;

    /*支付金额*/
    private String total_amount;

    /*订单名称*/
    private String subject;

    /*商品描述*/
    private String body;

}
