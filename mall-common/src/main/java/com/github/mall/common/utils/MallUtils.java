package com.github.mall.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-20
 * @description： 工具类
 */
public class MallUtils {

    /**
     * 判断是否为邮箱
     * create by: HPC
     * description: 是否为邮箱
     * create time: 2019/5/20
     *
     * @return
     * @Param: null
     */
    public static boolean isEmail(String email) {

        return false;
    }

    /**
     * 校验手机号码是否合法
     * create by: HPC
     * description:
     * create time: 2019/5/20
     *
     * @param phone   手机号码
     * @param isChina 是否为大陆 true为大陆，false为香港，null为不区分
     * @return boolean
     * @throws PatternSyntaxException
     */
    public static boolean isPhoneLegal(String phone, Boolean isChina) throws PatternSyntaxException {
        /*如果传的是null就说明不区分大陆和香港*/
        if (isChina == null) {
            return isChinaPhoneLegal(phone) || isHKPhoneLegal(phone);
        }
        /*true 为中国*/
        if (isChina) {
            return isChinaPhoneLegal(phone);
        } else {
            /*false为香港*/
            return isHKPhoneLegal(phone);
        }
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    private static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
