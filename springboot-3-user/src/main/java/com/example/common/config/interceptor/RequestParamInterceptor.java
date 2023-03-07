package com.example.common.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.common.exception.MyExceptionUtil;
import com.example.common.json.JsonResultEnum;
import com.example.utils.http.StreamUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器 - 请求参数判断是否冗余
 *
 * https://juejin.cn/post/6962053059241312293
 */
public class RequestParamInterceptor extends HandlerInterceptorAdapter {

    /**
     * 请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
            Map<String, Object> paramMap = getRequestParam(request);
            if (parameterTypes.length != 1) {
                return true;
            }
            Field[] fields = parameterTypes[0].getDeclaredFields();
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if (Arrays.stream(fields).noneMatch(field -> field.getName().equals(entry.getKey()))) {
                    MyExceptionUtil.throwException(JsonResultEnum.PARAMETER_ERROR.getCode(), "请求中存在冗余参数");
                }
            }
        }
        return true;
    }

    private Map<String, Object> getRequestParam(HttpServletRequest request) throws Exception {
        Map<String, Object> paramMap;
        if ("POST".equals(request.getMethod()) && !request.getInputStream().isFinished()) {
            String jsonParam = StreamUtil.read(request.getInputStream());
            paramMap = JSONObject.parseObject(jsonParam).getInnerMap();
        } else {
            paramMap = new HashMap<>();
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String key = (String) paramNames.nextElement();
                String value = request.getParameter(key);
                paramMap.put(key, value);
            }
        }
        return paramMap;
    }

    /**
     * 请求后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * finally
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
