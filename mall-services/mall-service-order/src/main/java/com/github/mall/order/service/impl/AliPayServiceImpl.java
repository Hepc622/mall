package com.github.mall.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.mall.common.dto.Result;
import com.github.mall.order.config.alibaba.AlipayConfig;
import com.github.mall.order.enums.AliPayTypeEnum;
import com.github.mall.order.service.AliPayService;
import com.github.mall.order.vo.AliPayRequestVo;
import com.github.mall.order.vo.AliPayResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-25
 * @description： 阿里支付sevice层
 */
@Service
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * create by: HPC
     * description: 统一收单下单并支付页面接口
     * create time: 2019/6/25
     */
    public Result<AliPayResponseVo> preparePay(AliPayRequestVo vo, HttpServletResponse response) {
        /*支付响应体*/
        AliPayResponseVo payResponseVo = new AliPayResponseVo();
        try {

            /*获取支付宝请求客户端*/
            AlipayClient alipayClient = getAlipayClient();

            /*web发起的支付*/
            if (AliPayTypeEnum.WEB_PAY.getValue().equals(vo.getPayType())) {
                //设置请求参数
                AlipayTradePagePayRequest webPayRequest = new AlipayTradePagePayRequest();
                webPayRequest.setReturnUrl(alipayConfig.getReturn_url());
                webPayRequest.setNotifyUrl(alipayConfig.getNotify_url());

                //组装json数据
                String bizContent = JSONObject.toJSONString(vo);
                webPayRequest.setBizContent(bizContent);
                /*发起下单请求*/
                String result = alipayClient.pageExecute(webPayRequest).getBody();

                /*设置响应体*/
                payResponseVo.setWebPayBody(result);

                return Result.success(payResponseVo);

                /*app 发起的支付*/
            } else if (AliPayTypeEnum.APP_PAY.getValue().equals(vo.getPayType())) {
                /*创建app下单请求实体*/
                AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();

                /*组装请求实体数据*/
                AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                /*自定义数据*/
                model.setBody(vo.getBody());
                /*订单名*/
                model.setSubject(vo.getSubject());
                /*订单号*/
                model.setOutTradeNo(vo.getOut_trade_no());
                /*超时时间*/
                model.setTimeoutExpress("30m");
                /*支付金额*/
                model.setTotalAmount(vo.getTotal_amount());
                model.setProductCode("QUICK_MSECURITY_PAY");
                appPayRequest.setBizModel(model);

                /*发起下单请求*/
                AlipayTradeAppPayResponse aliResponse = alipayClient.sdkExecute(appPayRequest);

                /*获取返回数据*/
                String body = aliResponse.getBody();

                /*设置请求响应体*/
                payResponseVo.setAppPayBody(body);

                return Result.success(payResponseVo);
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return Result.fail(payResponseVo);
    }

    /**
     * create by: HPC
     * description: 统一收单交易退款接口
     * create time: 2019/6/25
     */
    public Result refund(Object obj) {
        return null;
    }

    /**
     * create by: HPC
     * description: 统一收单交易退款查询接口
     * create time: 2019/6/25
     */
    public Result refundQuery(Object obj) {
        return null;
    }

    /**
     * create by: HPC
     * description: 统一收单交易关闭接口
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    public Result close(Object obj) {
        return null;
    }

    /**
     * create by: HPC
     * description: 支付通知回调
     * create time: 2019/6/25
     *
     * @return
     * @Param: null
     */
    public String notify(HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                try {
                    valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                params.put(name, valueStr);
            }

            /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
            */
            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipay_public_key(),
                    alipayConfig.getCharset(), alipayConfig.getSign_type()); //调用SDK验证签名

            //验证成功
            if (signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                }
            }
        } catch (Exception e) {
            log.error("支付宝回调本服务抛出异常: ", e);
            return "fail";

        }

        return "success";
    }

    /**
     * create by: HPC
     * description: 获取阿里支付客户端
     * create time: 2019/6/25
     *
     * @return
     * @Param
     */
    private AlipayClient getAlipayClient() {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getApp_id(),
                alipayConfig.getMerchant_private_key(), "json", alipayConfig.getCharset(),
                alipayConfig.getAlipay_public_key(), alipayConfig.getSign_type());
        return alipayClient;
    }
}
