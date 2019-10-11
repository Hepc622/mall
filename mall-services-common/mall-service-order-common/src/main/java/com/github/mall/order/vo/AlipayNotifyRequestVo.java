package com.github.mall.order.vo;

import lombok.Data;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-26
 * @description： 支付完成阿里回调请求实体
 */
@Data
public class AlipayNotifyRequestVo {
    /**商户订单号*/
    private String out_trade_no;
    /**支付宝交易号*/
    private String trade_no;
    /**交易状态*/
    private String trade_status;
}
