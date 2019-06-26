package com.github.mall.order.controller;

import com.github.mall.common.dto.Result;
import com.github.mall.order.service.AliPayService;
import com.github.mall.order.vo.AliPayRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-25
 * @description： 支付控制器
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    /**
     * create by: HPC
     * description: 统一收单下单并支付页面接口
     * create time: 2019/6/25
     *
     * @return
     */
    @RequestMapping(value = "/preparePay", method = {RequestMethod.POST, RequestMethod.GET})
    public void preparePay(AliPayRequestVo obj, HttpServletResponse response) {
        aliPayService.preparePay(obj, response);
    }

    /**
     * create by: HPC
     * description: 统一收单交易退款接口
     * create time: 2019/6/25
     */
    @RequestMapping(value = "/refund", method = {RequestMethod.POST})
    @ResponseBody
    public Result refund(@RequestBody Object obj) {
        return aliPayService.refund(obj);
    }


    /**
     * create by: HPC
     * description: 统一收单交易退款查询接口
     * create time: 2019/6/25
     */
    @RequestMapping(value = "/refundQuery", method = {RequestMethod.POST})
    @ResponseBody
    public Result refundQuery(@RequestBody Object obj) {
        return aliPayService.refundQuery(obj);
    }

    /**
     * create by: HPC
     * description: 统一收单交易关闭接口
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    @RequestMapping(value = "/close", method = {RequestMethod.POST})
    @ResponseBody
    public Result close(@RequestBody Object obj){
        return aliPayService.close(obj);
    }


    /**
     * create by: HPC
     * description: 支付通知回调
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    @RequestMapping(value = "/notify", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String notify(HttpServletRequest request) {
        return aliPayService.notify(request);
    }

}
