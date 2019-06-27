package com.github.mall.order.enums;

/**
 * @author ：HPC
 * @date ：Created in 2019-06-27
 * @description： 支付宝支付类别枚举
 */
public enum AliPayTypeEnum {
    WEB_PAY("WEB", 0),
    APP_PAY("APP", 1),
    MINI_PAY("小程序", 2),
    LIFE_PAY("生活号", 3),
    THIREE_PAY("第三方提供商", 4),
    ;
    private String key;
    private Integer value;

    AliPayTypeEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
