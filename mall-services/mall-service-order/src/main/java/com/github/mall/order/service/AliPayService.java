package com.github.mall.order.service;

import com.github.mall.common.dto.Result;
import com.github.mall.order.vo.AliPayRequestVo;
import com.github.mall.order.vo.AliPayResponseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-25
 * @description：阿里支付service层接口
 */
public interface AliPayService {
    /**
     * create by: HPC
     * description: 统一收单下单并支付页面接口
     * create time: 2019/6/25
     */
    Result<AliPayResponseVo> preparePay(AliPayRequestVo vo, HttpServletResponse response);

    /**
     * create by: HPC
     * description: 统一收单交易退款接口
     * create time: 2019/6/25
     */
    Result refund(Object obj);


    /**
     * create by: HPC
     * description: 统一收单交易退款查询接口
     * create time: 2019/6/25
     */
    Result refundQuery(Object obj);

    /**
     * create by: HPC
     * description: 统一收单交易关闭接口
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    Result close(Object obj);


    /**
     * create by: HPC
     * description: 支付通知回调
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    String notify(HttpServletRequest request);
}
