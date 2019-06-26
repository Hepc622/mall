package com.github.mall.common.utils;

import okhttp3.*;
import org.apache.commons.lang.StringUtils;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-28
 * @description： http请求工具类
 */
public class MallHttpUtils {

    /**
     * 同步
     * webservice请求工具
     *
     * @return
     */
    public static String webServiceRequest(String url, String soapAction, String strBody) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/xml");
        //设置soap请求头,并设置方法名和参数
        RequestBody body = RequestBody.create(mediaType, strBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("SOAPAction", soapAction)
                .addHeader("Content-Type", "text/xml; charset=utf-8")
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = response.code();
            if (200 == code) {
                String xmlResult = response.body().string();
                if (StringUtils.isNotEmpty(xmlResult)) {
                    xmlResult = xmlResult.replace("\r", "");
                    System.out.println("返回数据为--->" + xmlResult);
                    return xmlResult;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
