package com.github.mall.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * aop
 *
 * @author HPC
 * @create 2019-01-05
 */
@Component
@Aspect
@Slf4j
public class OptionsLogAspect {

    /**
     * 进入方法之前
     *
     * @param point
     * @throws Exception
     */
    public void beforeMethod(JoinPoint point) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        String requestPath = request.getRequestURI();
        // 请求路径
        String requestUrl = request.getPathInfo();
        // 记录方法开始执行的时间
        long startTimeMillis = System.currentTimeMillis();
        request.setAttribute("time", startTimeMillis);
        // 获取请求协议-http
        String scheme = request.getScheme();
        // 获取服务端口号 -8080
        int serverPort = request.getServerPort();
        //请求哪个class
        String className = signature.getClass().getName();
        //请求哪个方法
        String methodName = point.getSignature().getName();
        /*获取请求参数*/
        Object object = commonData(point);
        log.info("start>>>>>>>【scheme:{},port:{},url:{},method:{},in_params:{}】", scheme, serverPort, requestPath, methodName, object==null?"":object.toString());
    }

    /**
     * @return void
     * @throws
     * @Title: afterMethod
     * @Description: 返回时触发
     */
    public void afterMethod(Object result, ProceedingJoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Long startTime = (Long) request.getAttribute("time");
        // 记录方法执行完成的时间
        long endTimeMillis = System.currentTimeMillis();
        log.info("end>>>>>>>>>>>{}ms>>>>>>>【out_params:{}】", endTimeMillis - startTime, result==null?"":result.toString());
    }

    /**
     * @param pjp
     * @return void
     * @throws Throwable
     * @throws
     * @Title: doAround
     * @Description: 环绕触发
     */
    @ResponseBody
    @Around(value = "execution(* com.github.mall..*.*Controller.*(..)) || execution(* com.github.mall..*.*Rpc.*(..)))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        // 前置
        beforeMethod(pjp);
        result = pjp.proceed();
        afterMethod(result,pjp);
        return result;
    }

    /**
     * 请求参数
     *
     * @param point
     */
    private Object commonData(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取前端的请求参数
        Object[] o = point.getArgs();
        Object parameterObject = null;
        String[] values = ((CodeSignature) point.getSignature()).getParameterNames();
        if (null != o && o.length > 0) {
            Class<?> clazz = signature.getDeclaringType();
            String clazzName = clazz.getName();
            String methodName = point.getSignature().getName();
            //参数名称数组
            String[] paramNames = getFieldsName(clazz, clazzName, methodName);
            if (null != values && values.length > 0) {
                // 请求参数
                // 单参数时
                if (1 == values.length) {
                    // 获取参数数组的下标
                    Integer a = getFieldIndex(paramNames, values[0]);
                    if (null != a) {
                        parameterObject = o[a];
                        // 基本数据时添加上key
                        if (isBaseDataType(parameterObject)) {
                            Map<String, Object> map = new HashMap<String, Object>(1);
                            map.put(values[0], parameterObject);
                            parameterObject = map;
                        }
                    }

                } else {
                    // 多元素时组装数据
                    Map<String, Object> map = new HashMap<String, Object>(10);
                    for (String value : values) {
                        Integer a = getFieldIndex(paramNames, value);
                        if (null != a) {
                            Object parameter = o[a];
                            map.put(value, parameter);
                        }
                    }
                    parameterObject = map;

                }
            }
        }
        return parameterObject;
    }

    private static String[] getFieldsName(Class<?> cls, String clazzName, String methodName){
        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = cls.getMethods();
        Method method = null;
        for (Method m : methods) {
            if (methodName.equals(m.getName())) {
                method = m;
            }
        }
        if (method != null) {
            return parameterNameDiscoverer.getParameterNames(method);
        } else {
            return new String[0];
        }
    }

    private Integer getFieldIndex(String[] paramNames, String fieldName) {
        Integer index = null;
        for (int i = 0; i < paramNames.length; i++) {
            if (fieldName.equals(paramNames[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean isBaseDataType(Object o) {
        if (null != o) {
            if (o.getClass().equals(Long.class) ||
                    o.getClass().equals(Integer.class) ||
                    o.getClass().equals(Short.class) ||
                    o.getClass().equals(Double.class) ||
                    o.getClass().equals(Boolean.class) ||
                    o.getClass().equals(Float.class) ||
                    o.getClass().equals(BigDecimal.class) ||
                    o.getClass().equals(String.class)) {
                return true;
            }
        }
        return false;
    }
}
